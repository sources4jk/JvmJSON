package s4jk.jvm.serialization.objects

import s4jk.jvm.serialization.JsonStringManager
import java.util.*
import java.util.function.*
import java.util.function.Function
import java.util.stream.*

abstract class AbstractJsonArray protected constructor(private val list: MutableList<Any?>): IJA, Stream<Any?> {
    override val size get() = list.size

    @Suppress("UNCHECKED_CAST")
    override fun <T> get(index: Int): T? {
        return this.list[index] as? T
    }

    override fun add(element: Any?) {
        this.list.add(element)
    }

    override fun add(index: Int, element: Any?) {
        this.list.add(index, element)
    }

    override fun remove(element: Any?): Boolean {
        return this.list.remove(element)
    }

    override fun removeAt(index: Int): Any? {
        return this.list.removeAt(index)
    }

    override fun indexOf(element: Any?): Int {
        return this.list.indexOf(element)
    }

    override fun isEmpty(): Boolean {
        return this.list.isEmpty()
    }

    override fun toString(): String {
        return JsonStringManager.jsonArrayToString(this, 0, 1)
    }

    override fun toString(indent: Int): String {
        return JsonStringManager.jsonArrayToString(this, indent, 1)
    }

    override fun forEach(action: Consumer<in Any?>?) {
        return this.list.forEach(action)
    }

    override fun spliterator(): Spliterator<Any?> {
        return this.list.spliterator()
    }

    override fun iterator(): MutableIterator<Any?> {
        return this.list.iterator()
    }

    override fun close() {
        this.list.stream().close()
    }

    override fun isParallel(): Boolean {
        return this.list.stream().isParallel
    }

    override fun sequential(): Stream<Any?> {
        return this.list.stream().sequential()
    }

    override fun parallel(): Stream<Any?> {
        return this.list.stream().parallel()
    }

    override fun unordered(): Stream<Any?> {
        return this.list.stream().unordered()
    }

    override fun onClose(closeHandler: Runnable): Stream<Any?> {
        return this.list.stream().onClose(closeHandler)
    }

    override fun filter(predicate: Predicate<in Any?>?): Stream<Any?> {
        return this.list.stream().filter(predicate)
    }

    override fun <R : Any?> map(mapper: Function<in Any?, out R>?): Stream<R> {
        return this.list.stream().map(mapper)
    }

    override fun mapToInt(mapper: ToIntFunction<in Any?>?): IntStream {
        return this.list.stream().mapToInt(mapper)
    }

    override fun mapToLong(mapper: ToLongFunction<in Any?>?): LongStream {
        return this.list.stream().mapToLong(mapper)
    }

    override fun mapToDouble(mapper: ToDoubleFunction<in Any?>?): DoubleStream {
        return this.list.stream().mapToDouble(mapper)
    }

    override fun <R : Any?> flatMap(mapper: Function<in Any?, out Stream<out R>>?): Stream<R> {
        return this.list.stream().flatMap(mapper)
    }

    override fun flatMapToInt(mapper: Function<in Any?, out IntStream>?): IntStream {
        return this.list.stream().flatMapToInt(mapper)
    }

    override fun flatMapToLong(mapper: Function<in Any?, out LongStream>?): LongStream {
        return this.list.stream().flatMapToLong(mapper)
    }

    override fun flatMapToDouble(mapper: Function<in Any?, out DoubleStream>?): DoubleStream {
        return this.list.stream().flatMapToDouble(mapper)
    }

    override fun distinct(): Stream<Any?> {
        return this.list.stream().distinct()
    }

    override fun sorted(): Stream<Any?> {
        return this.list.stream().sorted()
    }

    override fun sorted(comparator: Comparator<in Any?>?): Stream<Any?> {
        return this.list.stream().sorted(comparator)
    }

    override fun peek(action: Consumer<in Any?>?): Stream<Any?> {
        return this.list.stream().peek(action)
    }

    override fun limit(maxSize: Long): Stream<Any?> {
        return this.list.stream().limit(maxSize)
    }

    override fun skip(n: Long): Stream<Any?> {
        return this.list.stream().skip(n)
    }

    override fun forEachOrdered(action: Consumer<in Any?>?) {
        return this.list.stream().forEachOrdered(action)
    }

    override fun toArray(): Array<Any> {
        return this.list.stream().toArray()
    }

    override fun <A : Any?> toArray(generator: IntFunction<Array<A>>?): Array<A> {
        return this.list.stream().toArray(generator)
    }

    override fun reduce(identity: Any?, accumulator: BinaryOperator<Any?>?): Any? {
        return this.list.stream().reduce(identity, accumulator)
    }

    override fun reduce(accumulator: BinaryOperator<Any?>?): Optional<Any?> {
        return this.list.stream().reduce(accumulator)
    }

    override fun <U : Any?> reduce(
        identity: U,
        accumulator: BiFunction<U, in Any?, U>?,
        combiner: BinaryOperator<U>?
    ): U {
        return this.list.stream().reduce(identity, accumulator, combiner)
    }

    override fun <R : Any?> collect(
        supplier: Supplier<R>?,
        accumulator: BiConsumer<R, in Any?>?,
        combiner: BiConsumer<R, R>?
    ): R {
        return this.list.stream().collect(supplier, accumulator, combiner)
    }

    override fun <R : Any?, A : Any?> collect(collector: Collector<in Any?, A, R>?): R {
        return this.list.stream().collect(collector)
    }

    override fun min(comparator: Comparator<in Any?>?): Optional<Any?> {
        return this.list.stream().min(comparator)
    }

    override fun max(comparator: Comparator<in Any?>?): Optional<Any?> {
        return this.list.stream().max(comparator)
    }

    override fun count(): Long {
        return this.list.stream().count()
    }

    override fun anyMatch(predicate: Predicate<in Any?>?): Boolean {
        return this.list.stream().anyMatch(predicate)
    }

    override fun allMatch(predicate: Predicate<in Any?>?): Boolean {
        return this.list.stream().allMatch(predicate)
    }

    override fun noneMatch(predicate: Predicate<in Any?>?): Boolean {
        return this.list.stream().noneMatch(predicate)
    }

    override fun findFirst(): Optional<Any?> {
        return this.list.stream().findFirst()
    }

    override fun findAny(): Optional<Any?> {
        return this.list.stream().findAny()
    }
}