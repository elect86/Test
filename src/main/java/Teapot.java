import jdk.incubator.foreign.CLinker;
import jdk.incubator.foreign.FunctionDescriptor;
import jdk.incubator.foreign.ResourceScope;
import jdk.incubator.foreign.SegmentAllocator;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

import static jdk.incubator.foreign.CLinker.*;
import static opengl.glut_h.*;

public class Teapot {
    private float rot = 0;

    Teapot(ResourceScope scope) {
        var all = SegmentAllocator.ofScope(scope);
        // Reset Background
        glClearColor(0f, 0f, 0f, 0f);
        // Setup Lighting
        glShadeModel(GL_SMOOTH());
        var pos = all.allocateArray(C_FLOAT, new float[] {0.0f, 15.0f, -15.0f, 0});
        glLightfv(GL_LIGHT0(), GL_POSITION(), pos);
        var spec = all.allocateArray(C_FLOAT, new float[] {1, 1, 1, 0});
        glLightfv(GL_LIGHT0(), GL_AMBIENT(), spec);
        glLightfv(GL_LIGHT0(), GL_DIFFUSE(), spec);
        glLightfv(GL_LIGHT0(), GL_SPECULAR(), spec);
        var shini = all.allocate(C_FLOAT, 113);
        glMaterialfv(GL_FRONT(), GL_SHININESS(), shini);
        glEnable(GL_LIGHTING());
        glEnable(GL_LIGHT0());
        glEnable(GL_DEPTH_TEST());
    }

    void display() {
        glClear(GL_COLOR_BUFFER_BIT() | GL_DEPTH_BUFFER_BIT());
        glPushMatrix();
        glRotatef(-20f, 1f, 1f, 0f);
        glRotatef(rot, 0f, 1f, 0f);
        glutSolidTeapot(0.5d);
        glPopMatrix();
        glutSwapBuffers();
    }

    void onIdle() {
        rot += 0.1;
        glutPostRedisplay();
    }

    public static void main(String[] args) {
        try (var scope = ResourceScope.newConfinedScope()) {
            var all = SegmentAllocator.ofScope(scope);
            var argc = all.allocate(C_INT, 0);
            glutInit(argc, argc);
            glutInitDisplayMode(GLUT_DOUBLE() | GLUT_RGB() | GLUT_DEPTH());
            glutInitWindowSize(500, 500);
            glutCreateWindow(CLinker.toCString("Hello Panama!", scope));
            var teapot = new Teapot(scope);
            var displayHandle = MethodHandles.lookup().bind(teapot, "display", MethodType.methodType(void.class));
            var displayStub = CLinker.getInstance().upcallStub(displayHandle, FunctionDescriptor.ofVoid(), scope);
            var idleHandle = MethodHandles.lookup().bind(teapot, "onIdle", MethodType.methodType(void.class));
            var idleStub = CLinker.getInstance().upcallStub(idleHandle, FunctionDescriptor.ofVoid(), scope);
//            var displayStub = glutDisplayFunc$func.allocate(teapot::display, scope);
//            var idleStub = glutIdleFunc$func.allocate(teapot::onIdle, scope);
            glutDisplayFunc(displayStub);
            glutIdleFunc(idleStub);
            glutMainLoop();
        } catch (NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}