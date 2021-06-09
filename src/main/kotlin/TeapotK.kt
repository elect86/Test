//import jdk.incubator.foreign.CLinker
//import jdk.incubator.foreign.FunctionDescriptor
//import jdk.incubator.foreign.ResourceScope
//import jdk.incubator.foreign.SegmentAllocator
//import opengl.glut_h.*
//import java.lang.invoke.MethodHandles
//import java.lang.invoke.MethodType
//
//class TeapotK internal constructor(scope: ResourceScope?) {
//    private var rot = 0f
//    fun display() {
//        glClear(GL_COLOR_BUFFER_BIT() or GL_DEPTH_BUFFER_BIT())
//        glPushMatrix()
//        glRotatef(-20f, 1f, 1f, 0f)
//        glRotatef(rot, 0f, 1f, 0f)
//        glutSolidTeapot(0.5)
//        glPopMatrix()
//        glutSwapBuffers()
//    }
//
//    fun onIdle() {
//        rot += 0.1f
//        glutPostRedisplay()
//    }
//
//    companion object {
//        @JvmStatic
//        fun main(args: Array<String>) {
//            println(System.getProperty("java.version"))
//            println(System.getProperty("java.library.path"))
//            System.setProperty("java.library.path", System.getProperty("java.library.path") + ":/usr/lib/x86_64-linux-gnu")
//            println(System.getProperty("java.library.path"))
//            try {
//                ResourceScope.newConfinedScope().use { scope ->
//                    val all = SegmentAllocator.ofScope(scope)
//                    val argc = all.allocate(CLinker.C_INT, 0)
//                    glutInit(argc, argc)
//                    glutInitDisplayMode(GLUT_DOUBLE() or GLUT_RGB() or GLUT_DEPTH())
//                    glutInitWindowSize(500, 500)
//                    glutCreateWindow(CLinker.toCString("Hello Panama!", scope))
//                    val teapotK = TeapotK(scope)
//                    val displayHandle = MethodHandles.lookup().bind(teapotK, "display", MethodType.methodType(Void.TYPE))
//                    val displayStub = CLinker.getInstance().upcallStub(displayHandle, FunctionDescriptor.ofVoid(), scope)
//                    val idleHandle = MethodHandles.lookup().bind(teapotK, "onIdle", MethodType.methodType(Void.TYPE))
//                    val idleStub = CLinker.getInstance().upcallStub(idleHandle, FunctionDescriptor.ofVoid(), scope)
//                    //            var displayStub = glutDisplayFunc$func.allocate(teapot::display, scope);
//                    //            var idleStub = glutIdleFunc$func.allocate(teapot::onIdle, scope);
//                    glutDisplayFunc(displayStub)
//                    glutIdleFunc(idleStub)
//                    glutMainLoop()
//                }
//            } catch (e: NoSuchMethodException) {
//                e.printStackTrace()
//            } catch (e: IllegalAccessException) {
//                e.printStackTrace()
//            }
//        }
//    }
//
//    init {
//        val all = SegmentAllocator.ofScope(scope)
//        // Reset Background
//        glClearColor(0f, 0f, 0f, 0f)
//        // Setup Lighting
//        glShadeModel(GL_SMOOTH())
//        val pos = all.allocateArray(CLinker.C_FLOAT, floatArrayOf(0.0f, 15.0f, -15.0f, 0f))
//        glLightfv(GL_LIGHT0(), GL_POSITION(), pos)
//        val spec = all.allocateArray(CLinker.C_FLOAT, floatArrayOf(1f, 1f, 1f, 0f))
//        glLightfv(GL_LIGHT0(), GL_AMBIENT(), spec)
//        glLightfv(GL_LIGHT0(), GL_DIFFUSE(), spec)
//        glLightfv(GL_LIGHT0(), GL_SPECULAR(), spec)
//        val shini = all.allocate(CLinker.C_FLOAT, 113)
//        glMaterialfv(GL_FRONT(), GL_SHININESS(), shini)
//        glEnable(GL_LIGHTING())
//        glEnable(GL_LIGHT0())
//        glEnable(GL_DEPTH_TEST())
//    }
//}