package org.s4jk.jvm.json.core

import org.s4jk.jvm.json.JsonStringManager
import java.util.Optional
import java.util.Spliterator
import java.util.function.*
import java.util.function.Function
import java.util.stream.*

abstract class AbstractJsonList protected constructor(
    private val list: MutableList<JsonValue>
): IJL, Stream<JsonValue> {

    override val size get() = this.list.size

    override fun isEmpty(): Boolean {
        return this.list.isEmpty()
    }

    override fun contains(element: Any?): Boolean {
        return this.list.contains(JsonValue.handle(element))
    }

    override fun containsAll(elements: Collection<Any?>): Boolean {
        return this.list.containsAll(elements.map { JsonValue.handle(it) })
    }

    override operator fun get(index: Int): JsonValue {
        return this.list[index]
    }

    override fun indexOf(element: Any?): Int {
        return this.list.indexOf(JsonValue.handle(element))
    }

    override fun lastIndexOf(element: Any?): Int {
        return this.list.lastIndexOf(JsonValue.handle(element))
    }

    override fun listIterator(): ListIterator<JsonValue> {
        return this.list.listIterator()
    }

    override fun listIterator(index: Int): ListIterator<JsonValue> {
        return this.list.listIterator(index)
    }

    override fun sub(from: Int, to: Int): IJL {
        return JsonList.from(this.list.subList(from, to))
    }

    override fun add(element: Any?): Boolean {
        return this.list.add(JsonValue.handle(element))
    }

    override fun add(index: Int, element: Any?) {
        return this.list.add(index, JsonValue.handle(element))
    }

    override fun addAll(elements: Collection<Any?>): Boolean {
        return this.list.addAll(elements.map { JsonValue.handle(it) })
    }

    override fun addAll(index: Int, elements: Collection<Any?>): Boolean {
        return this.list.addAll(index, elements.map { JsonValue.handle(it) })
    }

    override fun remove(element: Any?): Boolean {
        return this.list.remove(JsonValue.handle(element))
    }

    override fun removeAt(index: Int): JsonValue {
        return this.list.removeAt(index)
    }

    override fun removeAll(elements: Collection<Any?>): Boolean {
        return this.list.removeAll(elements.map { JsonValue.handle(it) })
    }

    override fun retainAll(elements: Collection<Any?>): Boolean {
        return this.list.retainAll(elements.map { JsonValue.handle(it) })
    }

    override fun clear() {
        return this.list.clear()
    }

    override fun toString(): String {
        return JsonStringManager.jsonListToString(this, 0, 1)
    }

    override fun toString(indent: Int): String {
        return JsonStringManager.jsonListToString(this, indent, 1)
    }

    override fun forEach(action: Consumer<in JsonValue>?) {
        return this.list.forEach(action)
    }

    override fun spliterator(): Spliterator<JsonValue> {
       return this.list.spliterator()
    }

    override fun iterator(): MutableIterator<JsonValue> {
        return this.list.iterator()
    }

    override fun close() {
        return this.list.stream().close()
    }

    override fun isParallel(): Boolean {
        return this.list.stream().isParallel
    }

    override fun sequential(): Stream<JsonValue> {
        return this.list.stream().sequential()
    }

    override fun parallel(): Stream<JsonValue> {
        return this.list.stream().parallel()
    }

    override fun unordered(): Stream<JsonValue> {
        return this.list.stream().unordered()
    }

    override fun onClose(closeHandler: Runnable): Stream<JsonValue> {
        return this.list.stream().onClose(closeHandler)
    }

    override fun filter(predicate: Predicate<in JsonValue>?): Stream<JsonValue> {
        return this.list.stream().filter(predicate)
    }

    override fun <R: Any?> map(mapper: Function<in JsonValue, out R>?): Stream<R> {
        return this.list.stream().map(mapper)
    }

    override fun mapToInt(mapper: ToIntFunction<in JsonValue>?): IntStream {
        return this.list.stream().mapToInt(mapper)
    }

    override fun mapToLong(mapper: ToLongFunction<in JsonValue>?): LongStream {
        return this.list.stream().mapToLong(mapper)
    }

    override fun mapToDouble(mapper: ToDoubleFunction<in JsonValue>?): DoubleStream {
        return this.list.stream().mapToDouble(mapper)
    }

    override fun <R: Any?> flatMap(mapper: Function<in JsonValue, out Stream<out R>>?): Stream<R> {
        return this.list.stream().flatMap(mapper)
    }

    override fun flatMapToInt(mapper: Function<in JsonValue, out IntStream>?): IntStream {
        return this.list.stream().flatMapToInt(mapper)
    }

    override fun flatMapToLong(mapper: Function<in JsonValue, out LongStream>?): LongStream {
        return this.list.stream().flatMapToLong(mapper)
    }

    override fun flatMapToDouble(mapper: Function<in JsonValue, out DoubleStream>?): DoubleStream {
        return this.list.stream().flatMapToDouble(mapper)
    }

    override fun distinct(): Stream<JsonValue> {
        return this.list.stream()
    }

    override fun sorted(): Stream<JsonValue> {
        return this.list.stream()
    }

    override fun sorted(comparator: Comparator<in JsonValue>?): Stream<JsonValue> {
        return this.list.stream()
    }

    override fun peek(action: Consumer<in JsonValue>?): Stream<JsonValue> {
        return this.list.stream()
    }

    override fun limit(maxSize: Long): Stream<JsonValue> {
        return this.list.stream()
    }

    override fun skip(n: Long): Stream<JsonValue> {
        return this.list.stream()
    }

    override fun takeWhile(predicate: Predicate<in JsonValue>?): Stream<JsonValue> {
        return super.takeWhile(predicate)
    }

    override fun dropWhile(predicate: Predicate<in JsonValue>?): Stream<JsonValue> {
        return super.dropWhile(predicate)
    }

    override fun forEachOrdered(action: Consumer<in JsonValue>?) {
        return this.list.stream().forEachOrdered(action)
    }

    override fun toArray(): Array<Any> {
        return this.list.stream().toArray()
    }

    override fun <A: Any?> toArray(generator: IntFunction<Array<A>>?): Array<A> {
        return this.list.stream().toArray(generator)
    }

    override fun reduce(identity: JsonValue?, accumulator: BinaryOperator<JsonValue>?): JsonValue {
        return this.list.stream().reduce(identity, accumulator)
    }

    override fun reduce(accumulator: BinaryOperator<JsonValue>?): Optional<JsonValue> {
        return this.list.stream().reduce(accumulator)
    }

    override fun <U: Any?> reduce(
        identity: U,
        accumulator: BiFunction<U, in JsonValue, U>?,
        combiner: BinaryOperator<U>?
    ): U {
        return this.list.stream().reduce(identity, accumulator, combiner)
    }

    override fun <R: Any?> collect(
        supplier: Supplier<R>?,
        accumulator: BiConsumer<R, in JsonValue>?,
        combiner: BiConsumer<R, R>?
    ): R {
        return this.list.stream().collect(supplier, accumulator, combiner)
    }

    override fun <R: Any?, A: Any?> collect(collector: Collector<in JsonValue, A, R>?): R {
        return this.list.stream().collect(collector)
    }

    override fun min(comparator: Comparator<in JsonValue>?): Optional<JsonValue> {
        return this.list.stream().min(comparator)
    }

    override fun max(comparator: Comparator<in JsonValue>?): Optional<JsonValue> {
        return this.list.stream().max(comparator)
    }

    override fun count(): Long {
        return this.list.stream().count()
    }

    override fun anyMatch(predicate: Predicate<in JsonValue>?): Boolean {
        return this.list.stream().anyMatch(predicate)
    }

    override fun allMatch(predicate: Predicate<in JsonValue>?): Boolean {
        return this.list.stream().allMatch(predicate)
    }

    override fun noneMatch(predicate: Predicate<in JsonValue>?): Boolean {
        return this.list.stream().noneMatch(predicate)
    }

    override fun findFirst(): Optional<JsonValue> {
        return this.list.stream().findFirst()
    }

    override fun findAny(): Optional<JsonValue> {
        return this.list.stream().findAny()
    }
}