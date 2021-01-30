/*
 * Copyright (c) 1997, 2017, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package java.util;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import sun.misc.SharedSecrets;

/**
 * 原文： Resizable-array implementation of the <tt>List</tt> interface.
 * 译文 List 接口基于数组实现。
 *
 *
 * Implements all optional list operations, and permits all elements, including
 * <tt>null</tt>.
 * 实现所有可选的列表操作，并允许所有元素，包括 null
 *
 *
 * 原文： In addition to implementing the <tt>List</tt> interface,
 * this class provides methods to manipulate the size of the array that is
 * used internally to store the list.
 * 译文：除了实现List接口之外，这个类还提供了一些方法来操纵数组的大小，数组在内部用于存储列表。
 *
 *
 * 原文：(This class is roughly equivalent to
 * <tt>Vector</tt>, except that it is unsynchronized.)
 *
 * 译文： 这个类大致相当于Vector，只是它是不同步的
 *
 *
 * 原文：<p>The <tt>size</tt>, <tt>isEmpty</tt>, <tt>get</tt>, <tt>set</tt>,
 * <tt>iterator</tt>, and <tt>listIterator</tt> operations run in constant
 * time.  The <tt>add</tt> operation runs in <i>amortized constant time</i>,
 * that is, adding n elements requires O(n) time.  All of the other operations
 * run in linear time (roughly speaking).  The constant factor is low compared
 * to that for the <tt>LinkedList</tt> implementation.
 * 译文：
 *      size、isEmpty、get、set、iterator和listIterator操作以固定时间运行。
 *      add操作以摊销的固定时间运行，也就是说，添加n个元素需要O（n）时间。所有其他操作都在线性时间内运行（粗略地说）。
 *      与LinkedList实现相比，常量因子较低。
 *
 *
 * 原文：<p>Each <tt>ArrayList</tt> instance has a <i>capacity</i>.  The capacity is
 * the size of the array used to store the elements in the list.  It is always
 * at least as large as the list size.  As elements are added to an ArrayList,
 * its capacity grows automatically.  The details of the growth policy are not
 * specified beyond the fact that adding an element has constant amortized
 * time cost.
 * 译文：
 *      每个ArrayList实例都有一个容量。容量是用于存储列表中元素的数组的大小。
 *      它总是至少和列表大小一样大。当元素添加到ArrayList时，它的容量会自动增长。
 *      除了添加一个元素具有固定的摊余时间成本这一事实之外，增长策略的细节没有被指定。
 *
 *
 * 原文：<p>An application can increase the capacity of an <tt>ArrayList</tt> instance
 * before adding a large number of elements using the <tt>ensureCapacity</tt>
 * operation.  This may reduce the amount of incremental reallocation.
 * 译文：
 *      在使用ensureCapacity操作添加大量元素之前，应用程序可以增加ArrayList实例的容量。
 *      这可能会减少增量重新分配的数量。
 *
 *
 * 原文：<p><strong>Note that this implementation is not synchronized.</strong>
 * If multiple threads access an <tt>ArrayList</tt> instance concurrently,
 * and at least one of the threads modifies the list structurally, it
 * <i>must</i> be synchronized externally.  (A structural modification is
 * any operation that adds or deletes one or more elements, or explicitly
 * resizes the backing array; merely setting the value of an element is not
 * a structural modification.)  This is typically accomplished by
 * synchronizing on some object that naturally encapsulates the list.
 * 译文：
 *      请注意，此实现不是同步的。如果多个线程同时访问一个ArrayList实例，
 *      并且至少有一个线程在结构上修改了该列表，则必须在外部对其进行同步。
 *     （结构修改是添加或删除一个或多个元素的任何操作，或显式调整支持数组的大小；仅设置元素的值不是结构修改。）
 *      这通常是通过在自然封装列表的某个对象上进行同步来完成的。
 *
 *
 * 原文：If no such object exists, the list should be "wrapped" using the
 * {@link Collections#synchronizedList Collections.synchronizedList}
 * method.  This is best done at creation time, to prevent accidental
 * unsynchronized access to the list:<pre>
 *   List list = Collections.synchronizedList(new ArrayList(...));</pre>
 * 译文：
 *      如果不存在这样的对象，则应该使用
 *      同步列表Collections.synchronizedList方法。这最好在创建时完成，以防止对列表的意外非同步访问
 *      List list = Collections.synchronizedList(new ArrayList(...));
 *
 *
 * 原文：<p><a name="fail-fast">
 * The iterators returned by this class's {@link #iterator() iterator} and
 * {@link #listIterator(int) listIterator} methods are <em>fail-fast</em>:</a>
 * if the list is structurally modified at any time after the iterator is
 * created, in any way except through the iterator's own
 * {@link ListIterator#remove() remove} or
 * {@link ListIterator#add(Object) add} methods, the iterator will throw a
 * {@link ConcurrentModificationException}.  Thus, in the face of
 * concurrent modification, the iterator fails quickly and cleanly, rather
 * than risking arbitrary, non-deterministic behavior at an undetermined
 * time in the future.
 * 译文：
 *      这个类的iterator和listIterator方法返回的迭代器是快速失败的。如果在迭代器创建之后的任何时候，
 *      列表在结构上被修改，除了通过迭代器自己的方法。如果删除或添加方法，迭代器将抛出ConcurrentModificationException。
 *      因此，在面对并发修改时，迭代器会快速而干净地失败，而不是冒着在将来不确定的时间出现任意的、不确定的行为的风险。
 *
 *
 * 原文：<p>Note that the fail-fast behavior of an iterator cannot be guaranteed
 * as it is, generally speaking, impossible to make any hard guarantees in the
 * presence of unsynchronized concurrent modification.  Fail-fast iterators
 * throw {@code ConcurrentModificationException} on a best-effort basis.
 * Therefore, it would be wrong to write a program that depended on this
 * exception for its correctness:  <i>the fail-fast behavior of iterators
 * should be used only to detect bugs.</i>
 * 译文：
 *      注意，不能保证迭代器的快速故障行为，因为一般来说，在存在非同步并发修改的情况下，不可能做出任何硬保证。
 *      失败的快速迭代器会尽最大努力抛出ConcurrentModificationException。
 *      因此，编写一个依赖于此异常来保证其正确性的程序是错误的：迭代器的fail-fast行为应该只用于检测bug。
 *
 *
 * 原文：<p>This class is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 * 译文：
 *      此类是Java集合框架的成员
 * @author  Josh Bloch
 * @author  Neal Gafter
 * @see     Collection
 * @see     List
 * @see     LinkedList
 * @see     Vector
 * @since   1.2
 */

public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable
{
    private static final long serialVersionUID = 8683452581122892189L;

    /**
     * 原文：Default initial capacity.
     * 译文：默认初始容量。
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 原文：Shared empty array instance used for empty instances.
     * 译文：用于空实例的共享空数组实例。
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};

    /**
     * 原文：Shared empty array instance used for default sized empty instances. We
     * distinguish this from EMPTY_ELEMENTDATA to know how much to inflate when
     * first element is added.
     * 译文：用于默认大小的空实例的共享空数组实例。我们将其与空元素数据区分开来，
     *      以了解添加第一个元素时要扩容多少。
     *
     */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     * 原文：The array buffer into which the elements of the ArrayList are stored.
     * The capacity of the ArrayList is the length of this array buffer. Any
     * empty ArrayList with elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA
     * will be expanded to DEFAULT_CAPACITY when the first element is added.
     * 译文：
     *      存储ArrayList元素的数组缓冲区。ArrayList的容量是此数组缓冲区的长度。
     *      当添加第一个元素时，elementData==DEFAULTCAPACITY_EMPTY_ELEMENTDATA的空ArrayList将扩展为DEFAULT_CAPACITY。
     */
    transient Object[] elementData; // 原文：non-private to simplify nested class access  译文：非私有，以简化嵌套类访问

    /**
     * 原文：The size of the ArrayList (the number of elements it contains).
     * 译文：ArrayList的大小（它包含的元素数）。
     *
     * @serial
     */
    private int size;

    /**
     * 原文：Constructs an empty list with the specified initial capacity.
     * 译文：构造具有指定初始容量的空列表。
     *
     * @param  initialCapacity  原文：the initial capacity of the list
     *                          译文：列表的初始容量
     * @throws IllegalArgumentException if the specified initial capacity
     *         is negative
     *                                   译文：如果指定的初始容量为负
     */
    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }

    /**
     * 原文：Constructs an empty list with an initial capacity of ten.
     * 译文：构造一个初始容量为10的空列表。
     */
    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    /**
     * 原文：Constructs a list containing the elements of the specified
     * collection, in the order they are returned by the collection's
     * iterator.
     * 译文：按照集合迭代器返回元素的顺序，构造一个包含指定集合元素的列表。
     *
     * @param c 原文：the collection whose elements are to be placed into this list
     *          译文：要将其元素放入此列表的集合
     * @throws NullPointerException 原文：if the specified collection is null
     *                              译文：如果指定的集合为空
     */
    public ArrayList(Collection<? extends E> c) {
        elementData = c.toArray();
        if ((size = elementData.length) != 0) {
            //原文： c.toArray might (incorrectly) not return Object[] (see 6260652)
            //译文： c.toArray 可能(错误地)不返回Object[] (请参见6260652)
            if (elementData.getClass() != Object[].class)
                elementData = Arrays.copyOf(elementData, size, Object[].class);
        } else {
            //原文： replace with empty array.
            //译文： 替换为空数组
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }

    /**
     * 原文：Trims the capacity of this <tt>ArrayList</tt> instance to be the
     * list's current size.  An application can use this operation to minimize
     * the storage of an <tt>ArrayList</tt> instance.
     * 译文：将此ArrayList实例的容量修剪为列表的当前大小。应用程序可以使用此操作最小化ArrayList实例的存储。
     */
    public void trimToSize() {
        modCount++;
        if (size < elementData.length) {
            elementData = (size == 0)
                    ? EMPTY_ELEMENTDATA
                    : Arrays.copyOf(elementData, size);
        }
    }

    /**
     * 原文：Increases the capacity of this <tt>ArrayList</tt> instance, if
     * necessary, to ensure that it can hold at least the number of elements
     * specified by the minimum capacity argument.
     * 译文：增加这个ArrayList实例的容量，如果
     *      必要时，以确保它至少可以容纳由最小容量参数指定的元素数。
     * @param   minCapacity   原文：the desired minimum capacity  译文：所需的最小容量
     */
    public void ensureCapacity(int minCapacity) {
        int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
                // 原文： any size if not default element table
                // 译文：任何大小（如果不是默认元素表）
                ? 0
                // 原文：larger than default for default empty table. It's already supposed to be at default size.
                // 译文：大于默认空表的默认值。它应该已经是默认大小了。
                : DEFAULT_CAPACITY;

        if (minCapacity > minExpand) {
            ensureExplicitCapacity(minCapacity);
        }
    }

    private static int calculateCapacity(Object[] elementData, int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }

    private void ensureCapacityInternal(int minCapacity) {
        ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
    }

    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;

        // 原文：overflow-conscious code
        // 译文：有溢出意识的代码
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }

    /**
     * 原文：The maximum size of array to allocate.
     * Some VMs reserve some header words in an array.
     * Attempts to allocate larger arrays may result in
     * OutOfMemoryError: Requested array size exceeds VM limit
     * 译文：要分配的最大数组大小。一些vm在数组中保留一些头字。
     *      尝试分配较大的数组可能会导致OutOfMemoryError:请求的数组大小超出VM限制
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * 原文：Increases the capacity to ensure that it can hold at least the
     * number of elements specified by the minimum capacity argument.
     * 译文：增加容量，以确保它至少可以容纳由minimum capacity参数指定的元素数。
     * @param minCapacity 原文： the desired minimum capacity  译文：所需的最小容量
     */
    private void grow(int minCapacity) {
        // 原文：overflow-conscious code
        // 译文：有溢出意识的代码
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // 原文：minCapacity is usually close to size, so this is a win:
        // 译文：minCapacity通常接近大小，因此这是一个胜利
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // 原文： overflow   译文：溢出
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    /**
     * 原文：Returns the number of elements in this list.
     * 译文：返回此列表中的元素数
     * @return 原文：the number of elements in this list  译文：此列表中的元素数
     */
    public int size() {
        return size;
    }

    /**
     * 原文：Returns <tt>true</tt> if this list contains no elements.
     * 译文：如果此列表不包含元素，则返回true
     * @return 原文： <tt>true</tt> if this list contains no elements
     *         译文：true 表示 如果此列表不包含任何元素
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 原文：Returns <tt>true</tt> if this list contains the specified element.
     * More formally, returns <tt>true</tt> if and only if this list contains
     * at least one element <tt>e</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
     * 译文：如果此列表包含指定的元素。更多形式上，返回true当且仅当此列表至少包含一个元素e，使得（o==null；？e==null:o.equals（e）。
     *
     * @param 原文：o element whose presence in this list is to be tested
     *        译文：o  该列表中存在的待测试元素
     * @return 原文： <tt>true</tt> if this list contains the specified element
     *         译文：true 如果此列表包含指定的元素
     */
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * 原文：Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     * 译文：返回此列表中指定元素第一次出现的索引，如果此列表不包含该元素，则返回-1。更正式地说，返回最低的索引i，
     *      以便（o==null ？get（i）==null:o.equals（get（i）），如果没有这样的索引，则为-1。
     */
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    /**
     * 原文：Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the highest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     * 译文：返回此列表中指定元素最后一次出现的索引，如果此列表不包含该元素，则返回-1。更正式地说，返回最高的索引i，
     *      以便（o==null ？get（i）==null:o.equals（get（i）），如果没有这样的索引，则为-1。
     */
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size-1; i >= 0; i--)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = size-1; i >= 0; i--)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    /**
     * 原文：Returns a shallow copy of this <tt>ArrayList</tt> instance.  (The
     * elements themselves are not copied.)
     * 译文：返回此ArrayList实例的浅层副本。（元素本身不会被复制。）
     * @return 原文：a clone of this <tt>ArrayList</tt> instance
     *         译文：此ArrayList实例的副本
     */
    public Object clone() {
        try {
            ArrayList<?> v = (ArrayList<?>) super.clone();
            v.elementData = Arrays.copyOf(elementData, size);
            v.modCount = 0;
            return v;
        } catch (CloneNotSupportedException e) {
            // 原文：this shouldn't happen, since we are Cloneable
            // 译文：这不应该发生，因为我们是可克隆的
            throw new InternalError(e);
        }
    }

    /**
     * 原文： Returns an array containing all of the elements in this list
     * in proper sequence (from first to last element).
     * 译文：以正确的顺序（从第一个元素到最后一个元素）返回包含此列表中所有元素的数组。
     *
     * 原文：<p>The returned array will be "safe" in that no references to it are
     * maintained by this list.  (In other words, this method must allocate
     * a new array).  The caller is thus free to modify the returned array.
     * 译文：返回的数组将是“安全的”，因为此列表不保留对其的引用。 （换句话说，此方法必须分配一个新数组）。
     *      因此，调用者可以自由修改返回的数组。
     *
     * 原文：<p>This method acts as bridge between array-based and collection-based
     * APIs.
     * 译文：此方法充当基于数组的API和基于集合的API之间的桥梁。
     *
     * @return 原文：an array containing all of the elements in this list in
     *         proper sequence
     *         译文：包含此列表中所有元素的序列按适当顺序的数组
     */
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    /**
     * 原文：Returns an array containing all of the elements in this list in proper
     * sequence (from first to last element); the runtime type of the returned
     * array is that of the specified array.  If the list fits in the
     * specified array, it is returned therein.  Otherwise, a new array is
     * allocated with the runtime type of the specified array and the size of
     * this list.
     * 译文：返回一个数组，该数组按正确的顺序包含此列表中的所有元素（从第一个元素到最后一个元素）；
     *      返回数组的运行时类型是指定数组的运行时类型。 如果列表适合指定的数组，则将其返回。
     *      否则，将使用指定数组的运行时类型和此列表的大小分配一个新数组。
     *
     *
     * 原文：<p>If the list fits in the specified array with room to spare
     * (i.e., the array has more elements than the list), the element in
     * the array immediately following the end of the collection is set to
     * <tt>null</tt>.  (This is useful in determining the length of the
     * list <i>only</i> if the caller knows that the list does not contain
     * any null elements.)
     * 原文：如果列表适合指定的数组并有剩余空间（即，数组中的元素多于列表），则紧接集合结束后的数组中的元素设置为null。
     *      （如果调用者知道列表不包含任何null元素，则这对于确定 仅 列表的长度很有用。）
     *
     *
     * @param 原文：a the array into which the elements of the list are to
     *          be stored, if it is big enough; otherwise, a new array of the
     *          same runtime type is allocated for this purpose.
     *        译文：一个列表元素要存储到的数组（如果足够大）； 否则，将为此分配一个具有相同运行时类型的新数组。
     *
     *
     * @return 原文：an array containing the elements of the list
     *         译文：包含列表元素的数组
     * @throws ArrayStoreException 原文：if the runtime type of the specified array
     *         is not a supertype of the runtime type of every element in
     *         this list
     *         译文：如果指定数组的运行时类型不是此列表中每个元素的运行时类型的超类型
     * @throws NullPointerException 原文：if the specified array is null
     *                              译文：如果指定的数组为null
     */
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            // 原文：Make a new array of a's runtime type, but my contents:
            // 译文：根据a运行时类型数组来创建一个新的数组，但是我的内容是：
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    // 原文：Positional Access Operations
    // 译文：位置访问操作

    @SuppressWarnings("unchecked")
    E elementData(int index) {
        return (E) elementData[index];
    }

    /**
     * 原文：Returns the element at the specified position in this list.
     * 译文：返回此列表中指定位置的元素。
     *
     * @param  原文：index index of the element to return
     *         译文：要返回的元素的索引
     * @return 原文：the element at the specified position in this list
     *         译文：此列表中指定位置的元素
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E get(int index) {
        rangeCheck(index);

        return elementData(index);
    }

    /**
     * 原文：Replaces the element at the specified position in this list with
     * the specified element.
     * 译文：用指定的元素替换此列表中指定位置的元素。
     *
     * @param index 原文：index of the element to replace
     *              译文：替换元素的索引
     * @param element 原文：element to be stored at the specified position
     *                译文：要存储在指定位置的元素
     * @return 原文：the element previously at the specified position
     *         译文：先前位于指定位置的元素
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E set(int index, E element) {
        rangeCheck(index);

        E oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }

    /**
     * 原文：Appends the specified element to the end of this list.
     * 译文：将指定的元素追加到此列表的末尾。
     * @param e 原文：element to be appended to this list
     *          译文：要添加到此列表的元素
     * @return 原文：<tt>true</tt> (as specified by {@link Collection#add})
     *         译文：true (如指定Collection)
     */
    public boolean add(E e) {
        ensureCapacityInternal(size + 1);  // 原文：Increments modCount!!  译文：增加modCount
        elementData[size++] = e;
        return true;
    }

    /**
     * 原文：Inserts the specified element at the specified position in this
     * list. Shifts the element currently at that position (if any) and
     * any subsequent elements to the right (adds one to their indices).
     * 译文：将指定的元素插入此列表中的指定位置。 将当前在该位置的元素（如果有）和任何后续元素右移（将其索引加一）。
     *
     * @param index 原文：index at which the specified element is to be inserted
     *              译文：指定元素要插入的索引
     * @param element 原文：element to be inserted
     *                译文：要插入的元素
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        ensureCapacityInternal(size + 1);  // 原文：Increments modCount!!  译文：增加modCount
        System.arraycopy(elementData, index, elementData, index + 1,
                size - index);
        elementData[index] = element;
        size++;
    }

    /**
     * 原文：Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their
     * indices).
     * 译文：删除此列表中指定位置的元素。将所有后续元素向左移动（从其索引中减去一个）
     * @param index 原文：the index of the element to be removed
     *              译文：要删除的元素的索引
     * @return 原文：the element that was removed from the list
     *         译文：从列表中删除的元素
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E remove(int index) {
        rangeCheck(index);

        modCount++;
        E oldValue = elementData(index);

        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                    numMoved);
        elementData[--size] = null; //原文：clear to let GC do its work  译文：明确让GC开展工作

        return oldValue;
    }

    /**
     * 原文：Removes the first occurrence of the specified element from this list,
     * if it is present.  If the list does not contain the element, it is
     * unchanged.  More formally, removes the element with the lowest index
     * <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>
     * (if such an element exists).  Returns <tt>true</tt> if this list
     * contained the specified element (or equivalently, if this list
     * changed as a result of the call).
     * 译文：如果存在指定元素，则从此列表中删除该元素的第一次出现。 如果列表不包含该元素，则该元素不变。 更正式地，删除具有最低索引i的元素，
     *      使(o==null?get(i)==null:o.equals(get(i)))（如果存在这样的元素）。如果此列表包含指定的元素，
     *      则返回true（或者等效地，如果此列表由于调用而更改），则返回true。
     * @param o 原文：element to be removed from this list, if present
     *          译文：要从此列表中删除的元素（如果存在）
     * @return 原文：<tt>true</tt> if this list contained the specified element
     *         译文：true  如果此列表包含指定的元素
     */
    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++)
                if (elementData[index] == null) {
                    fastRemove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (o.equals(elementData[index])) {
                    fastRemove(index);
                    return true;
                }
        }
        return false;
    }

    /*
     * 原文：Private remove method that skips bounds checking and does not
     * return the value removed.
     * 翻译：私有的remove方法，跳过边界检查，不返回已删除的值。
     */
    private void fastRemove(int index) {
        modCount++;
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                    numMoved);
        elementData[--size] = null; // 原文：clear to let GC do its work 译文：明确让GC开展工作
    }

    /**
     * 原文：Removes all of the elements from this list.  The list will
     * be empty after this call returns.
     * 译文：从此列表中删除所有元素。 该调用返回后，该列表将为空。
     */
    public void clear() {
        modCount++;

        // 原文：clear to let GC do its work
        // 译文：明确让GC开展工作
        for (int i = 0; i < size; i++)
            elementData[i] = null;

        size = 0;
    }

    /**
     * 原文：Appends all of the elements in the specified collection to the end of
     * this list, in the order that they are returned by the
     * specified collection's Iterator.  The behavior of this operation is
     * undefined if the specified collection is modified while the operation
     * is in progress.  (This implies that the behavior of this call is
     * undefined if the specified collection is this list, and this
     * list is nonempty.)
     * 译文：按照指定集合的Iterator返回的顺序，将指定集合中的所有元素追加到此列表的末尾。
     *      如果在操作进行过程中修改了指定的集合，则此操作的行为是不确定的。
     *      (这意味着如果指定的集合是此列表，并且此列表是非空的，则此调用的行为是不确定的。)
     *
     * @param c 原文：collection containing elements to be added to this list
     *          译文：包含要添加到此列表的元素的集合
     * @return 原文：<tt>true</tt> if this list changed as a result of the call
     *         译文：true 该列表是否因通话而改变
     * @throws NullPointerException if the specified collection is null
     */
    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);  // Increments modCount
        System.arraycopy(a, 0, elementData, size, numNew);
        size += numNew;
        return numNew != 0;
    }

    /**
     * 原文：Inserts all of the elements in the specified collection into this
     * list, starting at the specified position.  Shifts the element
     * currently at that position (if any) and any subsequent elements to
     * the right (increases their indices).  The new elements will appear
     * in the list in the order that they are returned by the
     * specified collection's iterator.
     * 译文：从指定位置开始，将指定集合中的所有元素插入此列表。将当前位于该位置的元素（如果有）和任何后续元素向右移动（增加其索引）。
     *      新元素将按指定集合的迭代器返回的顺序出现在列表中。
     * @param index 原文：index at which to insert the first element from the
     *              specified collection
     *              译文：从指定集合中插入第一个元素的索引
     * @param c 原文：collection containing elements to be added to this list
     *          译文：包含要添加到此列表的元素的集合
     * @return 原文：<tt>true</tt> if this list changed as a result of the call
     *         译文：true 该列表是否因通话而改变
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @throws NullPointerException 原文：if the specified collection is null
     *                              译文：如果指定的集合为null
     */
    public boolean addAll(int index, Collection<? extends E> c) {
        rangeCheckForAdd(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);  // Increments modCount

        int numMoved = size - index;
        if (numMoved > 0)
            System.arraycopy(elementData, index, elementData, index + numNew,
                    numMoved);

        System.arraycopy(a, 0, elementData, index, numNew);
        size += numNew;
        return numNew != 0;
    }

    /**
     * 原文：Removes from this list all of the elements whose index is between
     * {@code fromIndex}, inclusive, and {@code toIndex}, exclusive.
     * Shifts any succeeding elements to the left (reduces their index).
     * This call shortens the list by {@code (toIndex - fromIndex)} elements.
     * (If {@code toIndex==fromIndex}, this operation has no effect.)
     * 译文：从此列表中删除索引在 fromIndex（含）和 toIndex（互斥）之间的所有元素。
     *      将所有后续元素向左移动（减少其索引）。 此调用通过oIndex-fromIndex元素来缩短列表。 （
     *      如果toIndex == fromIndex，则此操作无效。）
     * @throws IndexOutOfBoundsException if {@code fromIndex} or
     *         {@code toIndex} is out of range
     *         ({@code fromIndex < 0 ||
     *          fromIndex >= size() ||
     *          toIndex > size() ||
     *          toIndex < fromIndex})
     */
    protected void removeRange(int fromIndex, int toIndex) {
        modCount++;
        int numMoved = size - toIndex;
        System.arraycopy(elementData, toIndex, elementData, fromIndex,
                numMoved);

        // clear to let GC do its work
        int newSize = size - (toIndex-fromIndex);
        for (int i = newSize; i < size; i++) {
            elementData[i] = null;
        }
        size = newSize;
    }

    /**
     * 原文：Checks if the given index is in range.  If not, throws an appropriate
     * runtime exception.  This method does *not* check if the index is
     * negative: It is always used immediately prior to an array access,
     * which throws an ArrayIndexOutOfBoundsException if index is negative.
     * 译文：检查给定的索引是否在范围内。 如果不是，则抛出适当的运行时异常。
     *      此方法不检查索引是否为负：始终在数组访问之前立即使用它，
     *      如果索引为负，则抛出ArrayIndexOutOfBoundsException。
     */
    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * 原文：A version of rangeCheck used by add and addAll.
     * 译文：add和addAll使用的rangeCheck版本。
     */
    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * 原文：Constructs an IndexOutOfBoundsException detail message.
     * Of the many possible refactorings of the error handling code,
     * this "outlining" performs best with both server and client VMs.
     * 译文：构造一个IndexOutOfBoundsException详细信息。 在错误处理代码的许多可能重构中，
     *      此“概述”在服务器和客户端VM上均表现最佳。
     */
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    /**
     * 原文：Removes from this list all of its elements that are contained in the
     * specified collection.
     * 译文：从此列表中删除指定集合中包含的所有元素。
     *
     * @param c 原文：collection containing elements to be removed from this list
     *          译文：包含要从此列表中删除的元素的集合
     * @return {@code true} 原文：if this list changed as a result of the call
     *                      译文：该列表是否因通话而改变
     * @throws ClassCastException 原文：if the class of an element of this list
     *         is incompatible with the specified collection
     *                            译文：如果此列表的元素的类与指定的集合不兼容
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException 原文：if this list contains a null element and the
     *         specified collection does not permit null elements
     *                              译文：如果此列表包含null元素，并且指定的集合不允许null元素
     * (<a href="Collection.html#optional-restrictions">optional</a>),
     *         or if the specified collection is null
     *         译文：或如果指定的集合为null
     * @see Collection#contains(Object)
     */
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        return batchRemove(c, false);
    }

    /**
     * 原文：Retains only the elements in this list that are contained in the
     * specified collection.  In other words, removes from this list all
     * of its elements that are not contained in the specified collection.
     * 译文：仅保留此列表中指定集合中包含的元素。 换句话说，从此列表中删除所有
     * @param c 原文：collection containing elements to be retained in this list
     *          译文：包含要保留在此列表中的元素的集合
     * @return {@code true} if this list changed as a result of the call
     * @throws ClassCastException 原文：if the class of an element of this list
     *         is incompatible with the specified collection
     *                            译文：如果此列表的元素的类与指定的集合不兼容
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException 原文：if this list contains a null element and the
     *         specified collection does not permit null elements
     *                              译文：如果此列表包含null元素，并且指定的集合不允许null元素
     * (<a href="Collection.html#optional-restrictions">optional</a>),
     *         原文：or if the specified collection is null
     *         译文：或如果指定的集合为null
     * @see Collection#contains(Object)
     */
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        return batchRemove(c, true);
    }

    private boolean batchRemove(Collection<?> c, boolean complement) {
        final Object[] elementData = this.elementData;
        int r = 0, w = 0;
        boolean modified = false;
        try {
            for (; r < size; r++)
                if (c.contains(elementData[r]) == complement)
                    elementData[w++] = elementData[r];
        } finally {
            // 原文：Preserve behavioral compatibility with AbstractCollection,
            // even if c.contains() throws.
            // 译文：即使c.contains（）抛出异常，仍保留与AbstractCollection的行为兼容性。
            if (r != size) {
                System.arraycopy(elementData, r,
                        elementData, w,
                        size - r);
                w += size - r;
            }
            if (w != size) {
                // clear to let GC do its work
                for (int i = w; i < size; i++)
                    elementData[i] = null;
                modCount += size - w;
                size = w;
                modified = true;
            }
        }
        return modified;
    }

    /**
     * 原文：Save the state of the <tt>ArrayList</tt> instance to a stream (that
     * is, serialize it).
     * 译文：将ArrayList实例的状态保存到流中（即，对其进行序列化）。
     *
     * @serialData 原文：The length of the array backing the <tt>ArrayList</tt>
     *             instance is emitted (int), followed by all of its elements
     *             (each an <tt>Object</tt>) in the proper order.
     *             译文：支持ArrayList实例的数组的长度（int），然后以适当的顺序跟随其所有元素（每个Object）。
     */
    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException{
        // 原文：Write out element count, and any hidden stuff
        // 译文：写出元素计数以及任何隐藏的内容
        int expectedModCount = modCount;
        s.defaultWriteObject();

        // 原文：Write out size as capacity for behavioural compatibility with clone()
        // 译文：写出大小作为与clone（）行为兼容的容量
        s.writeInt(size);

        // 原文：Write out all elements in the proper order.
        // 译文：按照正确的顺序写出所有元素。
        for (int i=0; i<size; i++) {
            s.writeObject(elementData[i]);
        }

        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }

    /**
     * 原文：Reconstitute the <tt>ArrayList</tt> instance from a stream (that is,
     * deserialize it).
     * 译文：从流中重构ArrayList实例（即，对其进行反序列化）
     */
    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException {
        elementData = EMPTY_ELEMENTDATA;

        // 原文：Read in size, and any hidden stuff
        // 译文：阅读大小以及任何隐藏的内容
        s.defaultReadObject();

        // 原文：Read in capacity
        // 译文：读入容量
        s.readInt(); // ignored

        if (size > 0) {
            // 原文：be like clone(), allocate array based upon size not capacity
            // 译文：像clone（）一样，根据大小而不是容量分配数组
            int capacity = calculateCapacity(elementData, size);
            SharedSecrets.getJavaOISAccess().checkArray(s, Object[].class, capacity);
            ensureCapacityInternal(size);

            Object[] a = elementData;
            // 原文：Read in all elements in the proper order.
            // 译文：按正确的顺序读入所有元素。
            for (int i=0; i<size; i++) {
                a[i] = s.readObject();
            }
        }
    }

    /**
     * 原文：Returns a list iterator over the elements in this list (in proper
     * sequence), starting at the specified position in the list.
     * The specified index indicates the first element that would be
     * returned by an initial call to {@link ListIterator#next next}.
     * An initial call to {@link ListIterator#previous previous} would
     * return the element with the specified index minus one.
     * 译文：从列表中的指定位置开始，以适当的顺序返回此列表中的元素的列表迭代器。
     *      指定的索引指示初始调用 ListIterator next将返回的第一个元素。
     *      初次调用 ListIterator previous将返回具有指定索引减一的元素。
     *
     * 原文：<p>The returned list iterator is <a href="#fail-fast"><i>fail-fast</i></a>.
     * 译文：返回的列表迭代器是fail-fast
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public ListIterator<E> listIterator(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: "+index);
        return new ListItr(index);
    }

    /**
     * 原文：Returns a list iterator over the elements in this list (in proper
     * sequence).
     * 译文：返回此列表中的元素的列表迭代器（按适当顺序）。
     *
     * 原文：<p>The returned list iterator is <a href="#fail-fast"><i>fail-fast</i></a>.
     * 译文：返回的列表迭代器是fail-fast
     * @see #listIterator(int)
     */
    public ListIterator<E> listIterator() {
        return new ListItr(0);
    }

    /**
     * 原文：Returns an iterator over the elements in this list in proper sequence.
     * 译文：以正确的顺序返回此列表中元素的迭代器。
     *
     * 原文：<p>The returned iterator is <a href="#fail-fast"><i>fail-fast</i></a>.
     * 译文：返回的列表迭代器是fail-fast
     * @return 原文：an iterator over the elements in this list in proper sequence
     *         译文：以正确的顺序遍历此列表中的元素的迭代器
     */
    public Iterator<E> iterator() {
        return new Itr();
    }

    /**
     * 原文：An optimized version of AbstractList.Itr
     * 译文：优化版本的AbstractList.Itr
     */
    private class Itr implements Iterator<E> {
        int cursor;       // 原文：index of next element to return  译文：下一个要返回的元素的索引
        int lastRet = -1; // 原文：index of last element returned; -1 if no such  译文：返回的最后一个元素的索引； -1（如果没有）
        int expectedModCount = modCount;

        public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        public E next() {
            checkForComodification();
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = ArrayList.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (E) elementData[lastRet = i];
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                ArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public void forEachRemaining(Consumer<? super E> consumer) {
            Objects.requireNonNull(consumer);
            final int size = ArrayList.this.size;
            int i = cursor;
            if (i >= size) {
                return;
            }
            final Object[] elementData = ArrayList.this.elementData;
            if (i >= elementData.length) {
                throw new ConcurrentModificationException();
            }
            while (i != size && modCount == expectedModCount) {
                consumer.accept((E) elementData[i++]);
            }
            // 原文：update once at end of iteration to reduce heap write traffic
            // 译文：在迭代结束时更新一次，以减少堆写入流量
            cursor = i;
            lastRet = i - 1;
            checkForComodification();
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    /**
     * 原文：An optimized version of AbstractList.ListItr
     * 译文：优化版本的AbstractList.ListItr
     */
    private class ListItr extends Itr implements ListIterator<E> {
        ListItr(int index) {
            super();
            cursor = index;
        }

        public boolean hasPrevious() {
            return cursor != 0;
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor - 1;
        }

        @SuppressWarnings("unchecked")
        public E previous() {
            checkForComodification();
            int i = cursor - 1;
            if (i < 0)
                throw new NoSuchElementException();
            Object[] elementData = ArrayList.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i;
            return (E) elementData[lastRet = i];
        }

        public void set(E e) {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                ArrayList.this.set(lastRet, e);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        public void add(E e) {
            checkForComodification();

            try {
                int i = cursor;
                ArrayList.this.add(i, e);
                cursor = i + 1;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

    /**
     * 原文：Returns a view of the portion of this list between the specified
     * {@code fromIndex}, inclusive, and {@code toIndex}, exclusive.  (If
     * {@code fromIndex} and {@code toIndex} are equal, the returned list is
     * empty.)  The returned list is backed by this list, so non-structural
     * changes in the returned list are reflected in this list, and vice-versa.
     * The returned list supports all of the optional list operations.
     * 译文：
     * 返回此列表在指定的 fromIndex（含）和toIndex（互斥）之间的视图。
     * （如果 fromIndex和 toIndex相等，则返回列表为空。）返回列表由该列表支持，
     * 因此返回列表中的非结构性更改反映在该列表中，反之， 反之亦然。 返回的列表支持所有可选列表操作。
     *
     * 原文：<p>This method eliminates the need for explicit range operations (of
     * the sort that commonly exist for arrays).  Any operation that expects
     * a list can be used as a range operation by passing a subList view
     * instead of a whole list.  For example, the following idiom
     * removes a range of elements from a list:
     * 译文：此方法消除了对显式范围操作（数组通常存在的那种范围）的需要。
     * 通过传递subList视图而不是整个列表，可以将期望列表的任何操作用作范围操作。
     * 例如，以下成语从列表中删除了一系列元素：
     * <pre>
     *      list.subList(from, to).clear();
     * </pre>
     * 原文：Similar idioms may be constructed for {@link #indexOf(Object)} and
     * {@link #lastIndexOf(Object)}, and all of the algorithms in the
     * {@link Collections} class can be applied to a subList.
     * 译文：可以为indexOf（Object）和lastIndexOf（Object）构建类似的习惯用法，
     * 并且Collections类中的所有算法都可以应用于子列表。
     *
     * 原文：<p>The semantics of the list returned by this method become undefined if
     * the backing list (i.e., this list) is <i>structurally modified</i> in
     * any way other than via the returned list.  (Structural modifications are
     * those that change the size of this list, or otherwise perturb it in such
     * a fashion that iterations in progress may yield incorrect results.)
     * 译文：如果后备列表（即此列表）以除通过返回列表之外的任何其他方式进行 结构化修改，
     * 则此方法返回的列表的语义将变得不确定。 （结构修改是指更改此列表的大小的结构修改，
     * 或以其他方式扰乱此列表的方式，使得正在进行的迭代可能会产生错误的结果。）
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    public List<E> subList(int fromIndex, int toIndex) {
        subListRangeCheck(fromIndex, toIndex, size);
        return new SubList(this, 0, fromIndex, toIndex);
    }

    static void subListRangeCheck(int fromIndex, int toIndex, int size) {
        if (fromIndex < 0)
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        if (toIndex > size)
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        if (fromIndex > toIndex)
            throw new IllegalArgumentException("fromIndex(" + fromIndex +
                    ") > toIndex(" + toIndex + ")");
    }

    private class SubList extends AbstractList<E> implements RandomAccess {
        private final AbstractList<E> parent;
        private final int parentOffset;
        private final int offset;
        int size;

        SubList(AbstractList<E> parent,
                int offset, int fromIndex, int toIndex) {
            this.parent = parent;
            this.parentOffset = fromIndex;
            this.offset = offset + fromIndex;
            this.size = toIndex - fromIndex;
            this.modCount = ArrayList.this.modCount;
        }

        public E set(int index, E e) {
            rangeCheck(index);
            checkForComodification();
            E oldValue = ArrayList.this.elementData(offset + index);
            ArrayList.this.elementData[offset + index] = e;
            return oldValue;
        }

        public E get(int index) {
            rangeCheck(index);
            checkForComodification();
            return ArrayList.this.elementData(offset + index);
        }

        public int size() {
            checkForComodification();
            return this.size;
        }

        public void add(int index, E e) {
            rangeCheckForAdd(index);
            checkForComodification();
            parent.add(parentOffset + index, e);
            this.modCount = parent.modCount;
            this.size++;
        }

        public E remove(int index) {
            rangeCheck(index);
            checkForComodification();
            E result = parent.remove(parentOffset + index);
            this.modCount = parent.modCount;
            this.size--;
            return result;
        }

        protected void removeRange(int fromIndex, int toIndex) {
            checkForComodification();
            parent.removeRange(parentOffset + fromIndex,
                    parentOffset + toIndex);
            this.modCount = parent.modCount;
            this.size -= toIndex - fromIndex;
        }

        public boolean addAll(Collection<? extends E> c) {
            return addAll(this.size, c);
        }

        public boolean addAll(int index, Collection<? extends E> c) {
            rangeCheckForAdd(index);
            int cSize = c.size();
            if (cSize==0)
                return false;

            checkForComodification();
            parent.addAll(parentOffset + index, c);
            this.modCount = parent.modCount;
            this.size += cSize;
            return true;
        }

        public Iterator<E> iterator() {
            return listIterator();
        }

        public ListIterator<E> listIterator(final int index) {
            checkForComodification();
            rangeCheckForAdd(index);
            final int offset = this.offset;

            return new ListIterator<E>() {
                int cursor = index;
                int lastRet = -1;
                int expectedModCount = ArrayList.this.modCount;

                public boolean hasNext() {
                    return cursor != SubList.this.size;
                }

                @SuppressWarnings("unchecked")
                public E next() {
                    checkForComodification();
                    int i = cursor;
                    if (i >= SubList.this.size)
                        throw new NoSuchElementException();
                    Object[] elementData = ArrayList.this.elementData;
                    if (offset + i >= elementData.length)
                        throw new ConcurrentModificationException();
                    cursor = i + 1;
                    return (E) elementData[offset + (lastRet = i)];
                }

                public boolean hasPrevious() {
                    return cursor != 0;
                }

                @SuppressWarnings("unchecked")
                public E previous() {
                    checkForComodification();
                    int i = cursor - 1;
                    if (i < 0)
                        throw new NoSuchElementException();
                    Object[] elementData = ArrayList.this.elementData;
                    if (offset + i >= elementData.length)
                        throw new ConcurrentModificationException();
                    cursor = i;
                    return (E) elementData[offset + (lastRet = i)];
                }

                @SuppressWarnings("unchecked")
                public void forEachRemaining(Consumer<? super E> consumer) {
                    Objects.requireNonNull(consumer);
                    final int size = SubList.this.size;
                    int i = cursor;
                    if (i >= size) {
                        return;
                    }
                    final Object[] elementData = ArrayList.this.elementData;
                    if (offset + i >= elementData.length) {
                        throw new ConcurrentModificationException();
                    }
                    while (i != size && modCount == expectedModCount) {
                        consumer.accept((E) elementData[offset + (i++)]);
                    }
                    // 原文：update once at end of iteration to reduce heap write traffic
                    // 译文：在迭代结束时更新一次，以减少堆写入流量
                    lastRet = cursor = i;
                    checkForComodification();
                }

                public int nextIndex() {
                    return cursor;
                }

                public int previousIndex() {
                    return cursor - 1;
                }

                public void remove() {
                    if (lastRet < 0)
                        throw new IllegalStateException();
                    checkForComodification();

                    try {
                        SubList.this.remove(lastRet);
                        cursor = lastRet;
                        lastRet = -1;
                        expectedModCount = ArrayList.this.modCount;
                    } catch (IndexOutOfBoundsException ex) {
                        throw new ConcurrentModificationException();
                    }
                }

                public void set(E e) {
                    if (lastRet < 0)
                        throw new IllegalStateException();
                    checkForComodification();

                    try {
                        ArrayList.this.set(offset + lastRet, e);
                    } catch (IndexOutOfBoundsException ex) {
                        throw new ConcurrentModificationException();
                    }
                }

                public void add(E e) {
                    checkForComodification();

                    try {
                        int i = cursor;
                        SubList.this.add(i, e);
                        cursor = i + 1;
                        lastRet = -1;
                        expectedModCount = ArrayList.this.modCount;
                    } catch (IndexOutOfBoundsException ex) {
                        throw new ConcurrentModificationException();
                    }
                }

                final void checkForComodification() {
                    if (expectedModCount != ArrayList.this.modCount)
                        throw new ConcurrentModificationException();
                }
            };
        }

        public List<E> subList(int fromIndex, int toIndex) {
            subListRangeCheck(fromIndex, toIndex, size);
            return new SubList(this, offset, fromIndex, toIndex);
        }

        private void rangeCheck(int index) {
            if (index < 0 || index >= this.size)
                throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }

        private void rangeCheckForAdd(int index) {
            if (index < 0 || index > this.size)
                throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }

        private String outOfBoundsMsg(int index) {
            return "Index: "+index+", Size: "+this.size;
        }

        private void checkForComodification() {
            if (ArrayList.this.modCount != this.modCount)
                throw new ConcurrentModificationException();
        }

        public Spliterator<E> spliterator() {
            checkForComodification();
            return new ArrayListSpliterator<E>(ArrayList.this, offset,
                    offset + this.size, this.modCount);
        }
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        final int expectedModCount = modCount;
        @SuppressWarnings("unchecked")
        final E[] elementData = (E[]) this.elementData;
        final int size = this.size;
        for (int i=0; modCount == expectedModCount && i < size; i++) {
            action.accept(elementData[i]);
        }
        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }

    /**
     * 原文：Creates a <em><a href="Spliterator.html#binding">late-binding</a></em>
     * and <em>fail-fast</em> {@link Spliterator} over the elements in this
     * list.
     * 译文：在此列表中的元素上创建一个 后期绑定 和 快速失败
     *
     * 原文：<p>The {@code Spliterator} reports {@link Spliterator#SIZED},
     * {@link Spliterator#SUBSIZED}, and {@link Spliterator#ORDERED}.
     * Overriding implementations should document the reporting of additional
     * characteristic values.
     * 译文：首要实现应记录其他特征值的报告。
     *
     * @return a {@code Spliterator} 原文：over the elements in this list  译文：超过此列表中的元素
     * @since 1.8
     */
    @Override
    public Spliterator<E> spliterator() {
        return new ArrayListSpliterator<>(this, 0, -1, 0);
    }

    /** 原文：Index-based split-by-two, lazily initialized Spliterator
     *  译文：基于索引的二分之一，延迟初始化的Spliterator*/
    static final class ArrayListSpliterator<E> implements Spliterator<E> {

        /*
         * If ArrayLists were immutable, or structurally immutable (no
         * adds, removes, etc), we could implement their spliterators
         * with Arrays.spliterator. Instead we detect as much
         * interference during traversal as practical without
         * sacrificing much performance. We rely primarily on
         * modCounts. These are not guaranteed to detect concurrency
         * violations, and are sometimes overly conservative about
         * within-thread interference, but detect enough problems to
         * be worthwhile in practice. To carry this out, we (1) lazily
         * initialize fence and expectedModCount until the latest
         * point that we need to commit to the state we are checking
         * against; thus improving precision.  (This doesn't apply to
         * SubLists, that create spliterators with current non-lazy
         * values).  (2) We perform only a single
         * ConcurrentModificationException check at the end of forEach
         * (the most performance-sensitive method). When using forEach
         * (as opposed to iterators), we can normally only detect
         * interference after actions, not before. Further
         * CME-triggering checks apply to all other possible
         * violations of assumptions for example null or too-small
         * elementData array given its size(), that could only have
         * occurred due to interference.  This allows the inner loop
         * of forEach to run without any further checks, and
         * simplifies lambda-resolution. While this does entail a
         * number of checks, note that in the common case of
         * list.stream().forEach(a), no checks or other computation
         * occur anywhere other than inside forEach itself.  The other
         * less-often-used methods cannot take advantage of most of
         * these streamlinings.
         *
         * 译文：如果ArrayList是不可变的，或者在结构上是不可变的（没有添加，删除等），
         * 则可以使用Arrays.spliterator实现它们的分隔符。取而代之的是，我们在遍历期间检测到了尽可能多的干扰，
         * 而又不牺牲太多性能。我们主要依靠modCounts。这些不能保证检测到并发冲突，并且有时对线程内干扰过于保守，
         * 但是可以检测到足够的问题，值得在实践中使用。为此，我们
         * （1）延迟初始化fence和ExpectedModCount，
         * 直到需要提交到要检查的状态的最新点为止；从而提高精度。 （这不适用于创建具有当前非惰性值的分隔符的子列表）。
         * （2）在forEach（对性能最敏感的方法）的末尾，我们仅执行一次ConcurrentModificationException检查。
         * 当使用forEach（与迭代器相对）时，我们通常只能在操作之后而不是操作之前检测干扰。进一步的CME触发检查适用于所有其他可能的假设违反，
         * 例如null（如果给定size（）则为太小的elementData数组），这可能仅是由于干扰而发生。这允许forEach的内部循环运行而无需任何进一步的检查，
         * 并简化了lambda分辨率。尽管这确实需要进行大量检查，但请注意，在list.stream（）。forEach（a）的常见情况下，除了在forEach自身内部之外，
         * 没有其他地方进行检查或其他计算。其他不常用的方法不能利用这些精简方法中的大多数。
         */

        private final ArrayList<E> list;
        private int index; // 原文：current index, modified on advance/split 译文：当前索引，在提前/拆分时修改
        private int fence; // 原文：-1 until used; then one past last index  译文：-1，直到使用； 然后是最后一个索引
        private int expectedModCount; // 原文：initialized when fence set    译文：设置栅栏时初始化

        /** 原文：Create new spliterator covering the given  range    译文：创建覆盖给定范围的新分离器 */
        ArrayListSpliterator(ArrayList<E> list, int origin, int fence,
                             int expectedModCount) {
            this.list = list; // 原文：OK if null unless traversed  译文：如果为null，除非遍历，则为OK
            this.index = origin;
            this.fence = fence;
            this.expectedModCount = expectedModCount;
        }

        private int getFence() { // 原文：initialize fence to size on first use  译文：首次使用时将栅栏初始化为大小
            int hi; // 原文：(a specialized variant appears in method forEach)   译文：一个专门的变体出现在forEach方法中
            ArrayList<E> lst;
            if ((hi = fence) < 0) {
                if ((lst = list) == null)
                    hi = fence = 0;
                else {
                    expectedModCount = lst.modCount;
                    hi = fence = lst.size;
                }
            }
            return hi;
        }

        public ArrayListSpliterator<E> trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid) ? null : // 原文：divide range in half unless too small  译文：除非太小，否则将范围分成两半
                    new ArrayListSpliterator<E>(list, lo, index = mid,
                            expectedModCount);
        }

        public boolean tryAdvance(Consumer<? super E> action) {
            if (action == null)
                throw new NullPointerException();
            int hi = getFence(), i = index;
            if (i < hi) {
                index = i + 1;
                @SuppressWarnings("unchecked") E e = (E)list.elementData[i];
                action.accept(e);
                if (list.modCount != expectedModCount)
                    throw new ConcurrentModificationException();
                return true;
            }
            return false;
        }

        public void forEachRemaining(Consumer<? super E> action) {
            int i, hi, mc; // hoist accesses and checks from loop
            ArrayList<E> lst; Object[] a;
            if (action == null)
                throw new NullPointerException();
            if ((lst = list) != null && (a = lst.elementData) != null) {
                if ((hi = fence) < 0) {
                    mc = lst.modCount;
                    hi = lst.size;
                }
                else
                    mc = expectedModCount;
                if ((i = index) >= 0 && (index = hi) <= a.length) {
                    for (; i < hi; ++i) {
                        @SuppressWarnings("unchecked") E e = (E) a[i];
                        action.accept(e);
                    }
                    if (lst.modCount == mc)
                        return;
                }
            }
            throw new ConcurrentModificationException();
        }

        public long estimateSize() {
            return (long) (getFence() - index);
        }

        public int characteristics() {
            return Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED;
        }
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        Objects.requireNonNull(filter);
        // 原文：figure out which elements are to be removed  译文：找出要删除的元素
        // 原文：any exception thrown from the filter predicate at this stage 译文：在此阶段从筛选谓词引发的任何异常
        // 原文：will leave the collection unmodified  译文：将使集合保持不变
        int removeCount = 0;
        final BitSet removeSet = new BitSet(size);
        final int expectedModCount = modCount;
        final int size = this.size;
        for (int i=0; modCount == expectedModCount && i < size; i++) {
            @SuppressWarnings("unchecked")
            final E element = (E) elementData[i];
            if (filter.test(element)) {
                removeSet.set(i);
                removeCount++;
            }
        }
        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }

        // 原文：shift surviving elements left over the spaces left by removed elements
        // 译文：将剩余的生存元素移到已删除元素剩余的空间上
        final boolean anyToRemove = removeCount > 0;
        if (anyToRemove) {
            final int newSize = size - removeCount;
            for (int i=0, j=0; (i < size) && (j < newSize); i++, j++) {
                i = removeSet.nextClearBit(i);
                elementData[j] = elementData[i];
            }
            for (int k=newSize; k < size; k++) {
                elementData[k] = null;  // Let gc do its work
            }
            this.size = newSize;
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            modCount++;
        }

        return anyToRemove;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void replaceAll(UnaryOperator<E> operator) {
        Objects.requireNonNull(operator);
        final int expectedModCount = modCount;
        final int size = this.size;
        for (int i=0; modCount == expectedModCount && i < size; i++) {
            elementData[i] = operator.apply((E) elementData[i]);
        }
        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
        modCount++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void sort(Comparator<? super E> c) {
        final int expectedModCount = modCount;
        Arrays.sort((E[]) elementData, 0, size, c);
        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
        modCount++;
    }
}
