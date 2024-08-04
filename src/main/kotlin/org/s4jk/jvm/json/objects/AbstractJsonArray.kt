package org.s4jk.jvm.json.objects

import org.s4jk.jvm.json.JsonStringManager
import org.s4jk.jvm.json.JsonUtils
import java.util.*
import java.util.function.*
import java.util.function.Function
import java.util.stream.*

abstract class AbstractJsonArray protected constructor(
    private val list: MutableList<ValueContainer<Any?>>
): IJA, Stream<ValueContainer<Any?>> {

    override val size get() = list.size

    override fun get(index: Int): ValueContainer<Any?> {
        return this.list.getOrElse(index) { ValueContainer.NULL }
    }

    override fun add(element: Any?): Boolean {
        return this.list.add(ValueContainer(JsonUtils.resolveJsonValue(element)))
    }

    override fun add(index: Int, element: Any?) {
        return this.list.add(index, ValueContainer(JsonUtils.resolveJsonValue(element)))
    }

    override fun remove(element: Any?): Boolean {
        return this.list.remove(ValueContainer(element))
    }

    override fun removeAt(index: Int): ValueContainer<Any?> {
        return this.list.removeAt(index)
    }

    override fun indexOf(element: Any?): Int {
        return this.list.indexOf(ValueContainer(element))
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

    override fun forEach(action: Consumer<in ValueContainer<Any?>>?) {
        return this.list.forEach(action)
    }

    override fun spliterator(): Spliterator<ValueContainer<Any?>> {
        return this.list.spliterator()
    }

    override fun iterator(): MutableIterator<ValueContainer<Any?>> {
        return this.list.iterator()
    }

    override fun close() {
        return this.list.stream().close()
    }

    override fun isParallel(): Boolean {
        return this.list.stream().isParallel
    }

    override fun sequential(): Stream<ValueContainer<Any?>> {
        return this.list.stream().sequential()
    }

    override fun parallel(): Stream<ValueContainer<Any?>> {
        return this.list.stream().parallel()
    }

    override fun unordered(): Stream<ValueContainer<Any?>> {
        return this.list.stream().unordered()
    }

    override fun onClose(closeHandler: Runnable): Stream<ValueContainer<Any?>> {
        return this.list.stream().onClose(closeHandler)
    }

    override fun filter(predicate: Predicate<in ValueContainer<Any?>>?): Stream<ValueContainer<Any?>> {
        return this.list.stream().filter(predicate)
    }

    override fun <R: Any?> map(mapper: Function<in ValueContainer<Any?>, out R>?): Stream<R> {
        return this.list.stream().map(mapper)
    }

    override fun mapToInt(mapper: ToIntFunction<in ValueContainer<Any?>>?): IntStream {
        return this.list.stream().mapToInt(mapper)
    }

    override fun mapToLong(mapper: ToLongFunction<in ValueContainer<Any?>>?): LongStream {
        return this.list.stream().mapToLong(mapper)
    }

    override fun mapToDouble(mapper: ToDoubleFunction<in ValueContainer<Any?>>?): DoubleStream {
        return this.list.stream().mapToDouble(mapper)
    }

    override fun <R: Any?> flatMap(mapper: Function<in ValueContainer<Any?>, out Stream<out R>>?): Stream<R> {
        return this.list.stream().flatMap(mapper)
    }

    override fun flatMapToInt(mapper: Function<in ValueContainer<Any?>, out IntStream>?): IntStream {
        return this.list.stream().flatMapToInt(mapper)
    }

    override fun flatMapToLong(mapper: Function<in ValueContainer<Any?>, out LongStream>?): LongStream {
        return this.list.stream().flatMapToLong(mapper)
    }

    override fun flatMapToDouble(mapper: Function<in ValueContainer<Any?>, out DoubleStream>?): DoubleStream {
        return this.list.stream().flatMapToDouble(mapper)
    }

    override fun distinct(): Stream<ValueContainer<Any?>> {
        return this.list.stream()
    }

    override fun sorted(): Stream<ValueContainer<Any?>> {
        return this.list.stream()
    }

    override fun sorted(comparator: Comparator<in ValueContainer<Any?>>?): Stream<ValueContainer<Any?>> {
        return this.list.stream()
    }

    override fun peek(action: Consumer<in ValueContainer<Any?>>?): Stream<ValueContainer<Any?>> {
        return this.list.stream()
    }

    override fun limit(maxSize: Long): Stream<ValueContainer<Any?>> {
        return this.list.stream()
    }

    override fun skip(n: Long): Stream<ValueContainer<Any?>> {
        return this.list.stream()
    }

    override fun takeWhile(predicate: Predicate<in ValueContainer<Any?>>?): Stream<ValueContainer<Any?>> {
        return super.takeWhile(predicate)
    }

    override fun dropWhile(predicate: Predicate<in ValueContainer<Any?>>?): Stream<ValueContainer<Any?>> {
        return super.dropWhile(predicate)
    }

    override fun forEachOrdered(action: Consumer<in ValueContainer<Any?>>?) {
        return this.list.stream().forEachOrdered(action)
    }

    override fun toArray(): Array<Any> {
        return this.list.stream().toArray()
    }

    override fun <A: Any?> toArray(generator: IntFunction<Array<A>>?): Array<A> {
        return this.list.stream().toArray(generator)
    }

    override fun reduce(identity: ValueContainer<Any?>?, accumulator: BinaryOperator<ValueContainer<Any?>>?): ValueContainer<Any?> {
        return this.list.stream().reduce(identity, accumulator)
    }

    override fun reduce(accumulator: BinaryOperator<ValueContainer<Any?>>?): Optional<ValueContainer<Any?>> {
        return this.list.stream().reduce(accumulator)
    }

    override fun <U: Any?> reduce(
        identity: U,
        accumulator: BiFunction<U, in ValueContainer<Any?>, U>?,
        combiner: BinaryOperator<U>?
    ): U {
        return this.list.stream().reduce(identity, accumulator, combiner)
    }

    override fun <R: Any?> collect(
        supplier: Supplier<R>?,
        accumulator: BiConsumer<R, in ValueContainer<Any?>>?,
        combiner: BiConsumer<R, R>?
    ): R {
        return this.list.stream().collect(supplier, accumulator, combiner)
    }

    override fun <R: Any?, A: Any?> collect(collector: Collector<in ValueContainer<Any?>, A, R>?): R {
        return this.list.stream().collect(collector)
    }

    override fun min(comparator: Comparator<in ValueContainer<Any?>>?): Optional<ValueContainer<Any?>> {
        return this.list.stream().min(comparator)
    }

    override fun max(comparator: Comparator<in ValueContainer<Any?>>?): Optional<ValueContainer<Any?>> {
        return this.list.stream().max(comparator)
    }

    override fun count(): Long {
        return this.list.stream().count()
    }

    override fun anyMatch(predicate: Predicate<in ValueContainer<Any?>>?): Boolean {
        return this.list.stream().anyMatch(predicate)
    }

    override fun allMatch(predicate: Predicate<in ValueContainer<Any?>>?): Boolean {
        return this.list.stream().allMatch(predicate)
    }

    override fun noneMatch(predicate: Predicate<in ValueContainer<Any?>>?): Boolean {
        return this.list.stream().noneMatch(predicate)
    }

    override fun findFirst(): Optional<ValueContainer<Any?>> {
        return this.list.stream().findFirst()
    }

    override fun findAny(): Optional<ValueContainer<Any?>> {
        return this.list.stream().findAny()
    }
}