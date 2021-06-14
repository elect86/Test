package core

import jdk.incubator.foreign.*
import jdk.incubator.foreign.CLinker.*
import jdk.incubator.foreign.MemoryLayouts.JAVA_INT
import jdk.incubator.foreign.ResourceScope
////import jdk.internal.foreign.ResourceScopeImpl
//import java.lang.invoke.MethodType
//import java.lang.ref.Cleaner
//import java.nio.charset.Charset

fun main() {
    println(System.getProperty("java.version"))
}

//fun test() {
//
//    //    val cleaner = Cleaner.create()
//    confinedScope {
//        val arr = MemorySegment.allocateNative(MemoryLayout.sequenceLayout(5, JAVA_INT), this)
//        for (i in 0..5)
//            MemoryAccess.setIntAtIndex(arr, i.toLong(), i)
//    }
//
//    ResourceScope.newConfinedScope().use { scope ->
//        val allocator = SegmentAllocator.ofScope(scope)
//        val arr = allocator.allocateArray(JAVA_INT, intArrayOf(0, 1, 2, 3, 4))
//        scope.toCString("")
//
//        var errMsgPtrPtr = allocator.allocate(C_POINTER)
//
//        // sqlite3** dbPtrPtr;
//        var dbPtrPtr = allocator.allocate(C_POINTER)
//    }
//
//
//    scope {
////        val arr = cIntArrayOf(0, 1, 2, 3, 4)
//
////        toCString(
//    }
//    val mtype = MethodType.methodType(Void.TYPE, MemorySegment::class.java)
//    val SYSTEMTIME = MemoryLayout.structLayout(
//        C_SHORT.withName("wYear"), C_SHORT.withName("wMonth"),
//        C_SHORT.withName("wDayOfWeek"), C_SHORT.withName("wDay"),
//        C_SHORT.withName("wHour"), C_SHORT.withName("wMinute"),
//        C_SHORT.withName("wSecond"), C_SHORT.withName("wMilliseconds")
//    )
//    val fdesc = FunctionDescriptor.ofVoid(SYSTEMTIME)
//}
//
//// ResourceScope.java
//
//fun globalScope(block: ResourceScope.() -> Unit) = ResourceScope.globalScope().use { it.block() } // TODO we shouldnt `use`?
//fun confinedScope(block: ResourceScope.() -> Unit) = ResourceScope.newConfinedScope().use { it.block() }
//fun confinedScope(cleaner: Cleaner, block: ResourceScope.() -> Unit) = ResourceScope.newConfinedScope(cleaner).use { it.block() }
//fun implicitScope(block: ResourceScope.() -> Unit) = ResourceScope.newImplicitScope().use { it.block() }
//fun sharedScope(block: ResourceScope.() -> Unit) = ResourceScope.newSharedScope().use { it.block() }
//fun sharedScope(cleaner: Cleaner, block: ResourceScope.() -> Unit) = ResourceScope.newSharedScope(cleaner).use { it.block() }
//
//// [kool]
//
//fun scope(block: ResourceScope.() -> Unit) = confinedScope(block)
//fun scope(cleaner: Cleaner, block: ResourceScope.() -> Unit) = confinedScope(cleaner, block)
//operator fun ResourceScope.invoke(block: ResourceScope.() -> Unit) = use { block() }
//val gScope = ResourceScope.globalScope()
//
//
//// SegmentAllocator.java
//
////val ResourceScope.impl: ResourceScopeImpl
////    get() = this as ResourceScopeImpl
////
////fun ResourceScope.allocate(layout: ValueLayout, value: Byte): MemorySegment = impl.allocate(layout, value)
////fun ResourceScope.allocate(layout: ValueLayout, value: Char): MemorySegment = impl.allocate(layout, value)
////fun ResourceScope.allocate(layout: ValueLayout, value: Short): MemorySegment = impl.allocate(layout, value)
////fun ResourceScope.allocate(layout: ValueLayout, value: Int): MemorySegment = impl.allocate(layout, value)
////fun ResourceScope.allocate(layout: ValueLayout, value: Float): MemorySegment = impl.allocate(layout, value)
////fun ResourceScope.allocate(layout: ValueLayout, value: Long): MemorySegment = impl.allocate(layout, value)
////fun ResourceScope.allocate(layout: ValueLayout, value: Double): MemorySegment = impl.allocate(layout, value)
////fun ResourceScope.allocate(layout: ValueLayout, value: Addressable): MemorySegment = impl.allocate(layout, value)
////
////fun ResourceScope.allocateArray(elementLayout: ValueLayout, array: ByteArray): MemorySegment = impl.allocateArray(elementLayout, array)
////fun ResourceScope.allocateArray(elementLayout: ValueLayout, array: ShortArray): MemorySegment = impl.allocateArray(elementLayout, array)
////fun ResourceScope.allocateArray(elementLayout: ValueLayout, array: CharArray): MemorySegment = impl.allocateArray(elementLayout, array)
////fun ResourceScope.allocateArray(elementLayout: ValueLayout, array: IntArray): MemorySegment = impl.allocateArray(elementLayout, array)
////fun ResourceScope.allocateArray(elementLayout: ValueLayout, array: FloatArray): MemorySegment = impl.allocateArray(elementLayout, array)
////fun ResourceScope.allocateArray(elementLayout: ValueLayout, array: LongArray): MemorySegment = impl.allocateArray(elementLayout, array)
////fun ResourceScope.allocateArray(elementLayout: ValueLayout, array: DoubleArray): MemorySegment = impl.allocateArray(elementLayout, array)
////fun ResourceScope.allocateArray(elementLayout: ValueLayout, array: Array<Addressable>): MemorySegment = impl.allocateArray(elementLayout, array)
////
////// [kool]
////fun ResourceScope.cByteArrayOf(vararg elements: Byte): MemorySegment = impl.allocateArray(C_CHAR, elements)
////fun ResourceScope.cShortArrayOf(vararg elements: Short): MemorySegment = impl.allocateArray(C_SHORT, elements)
//////fun ResourceScope.allocateArray(elementLayout: ValueLayout, array: CharArray): MemorySegment = impl.allocateArray(elementLayout, array)
////fun ResourceScope.cIntArrayOf(vararg elements: Int): MemorySegment = impl.allocateArray(C_INT, elements)
////fun ResourceScope.cFloatArrayOf(vararg elements: Float): MemorySegment = impl.allocateArray(C_FLOAT, elements)
////fun ResourceScope.cLongArrayOf(vararg elements: Long): MemorySegment = impl.allocateArray(C_LONG, elements)
////fun ResourceScope.cDoubleArrayOf(vararg elements: Double): MemorySegment = impl.allocateArray(C_DOUBLE, elements)
////
////infix fun ResourceScope.allocate(layout: MemoryLayout): MemorySegment = impl.allocate(layout)
////
////fun ResourceScope.allocateArray(elementLayout: MemoryLayout, count: Long): MemorySegment = impl.allocateArray(elementLayout, count)
////
////infix fun ResourceScope.allocate(bytesSize: Long): MemorySegment = impl.allocate(bytesSize)
////
////fun ResourceScope.allocate(bytesSize: Long, bytesAlignment: Long): MemorySegment = impl.allocate(bytesSize, bytesAlignment)
//
//infix fun ResourceScope.arenaAllocator(size: Long): SegmentAllocator = SegmentAllocator.arenaAllocator(size, this)
//
//val ResourceScope.arenaAllocator: SegmentAllocator
//    get() = SegmentAllocator.arenaAllocator(this)
//
//val MemorySegment.allocator: SegmentAllocator
//    get() = SegmentAllocator.ofSegment(this)
//
//val ResourceScope.allocator: SegmentAllocator
//    get() = SegmentAllocator.ofScope(this)
//
//
//// CLinker.java
//
//infix fun ResourceScope.toCString(str: String): MemorySegment = CLinker.toCString(str, allocator)
//
//fun ResourceScope.toCString(str: String, charset: Charset): MemorySegment = CLinker.toCString(str, charset, allocator)
//
//val MemoryAddress.string: String
//    get() = CLinker.toJavaString(this)
//
//infix fun MemoryAddress.string(charset: Charset): String = CLinker.toJavaString(this, charset)
//
//val MemorySegment.string: String
//    get() = CLinker.toJavaString(this)
//
//infix fun MemorySegment.string(charset: Charset): String = CLinker.toJavaString(this, charset)