package com.xy.rx

import android.util.Log
import android.util.Log.i
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.functions.Function
import org.junit.Test

import org.junit.Assert.*

/**
 * Unit test for map operators.
 */
class MapOperatorTest {

    private val list by lazy { listOf(1, 2, 3, 4, 5, 6) }
    private val listWithNull by lazy { listOf(1, null, 3, null, 5, null) }

    /**
     * map
     */
    @Test
    @Throws(Exception::class)
    fun map() {
//        Observable.fromIterable(list)
//                .map { it * 2 }
//                .subscribe({
//                    System.out.println("map() onNext $it")
//                }, {
//                    System.out.println("map() onError ${it.localizedMessage}")
//                }, {
//                    System.out.println("map() onComplete")
//                })

        Observable.just(list)
                .map(Function<List<Int>, List<Int>>() {
                    val list = ArrayList<Int>()
                    for (i in it) {
                        list.add(i * 2)
                    }
                    list
                })
                .subscribe({
                    System.out.println("map() onNext $it")
                }, {
                    System.out.println("map() onError ${it.localizedMessage}")
                }, {
                    System.out.println("map() onComplete")
                })
    }

    /**
     * flatMap
     *
     * FlatMap操作符使用一个指定的函数对原始Observable发射的每一项数据执行变换操作，
     * 这个函数返回一个本身也发射数据的Observable，
     * 然后FlatMap合并这些Observables发射的数据，
     * 最后将合并后的结果当做它自己的数据序列发射。
     */
    @Test
    @Throws(Exception::class)
    fun flatMap() {
//        Observable.fromIterable(list)
//                .flatMap(Function<Int, ObservableSource<Int>> {
//                    Observable.just(it * 2)
//                })
//                .subscribe({
//                    System.out.println("flatMap() onNext $it")
//                }, {
//                    System.out.println("flatMap() onError ${it.localizedMessage}")
//                }, {
//                    System.out.println("flatMap() onComplete")
//                })

        Observable.just(list)
                .flatMap(Function<List<Int>, ObservableSource<List<Int>>>() {
                    val list = ArrayList<Int>()
                    for (i in it) {
                        list.add(i * 2)
                    }
                    Observable.just(list)
                })
                .subscribe({
                    System.out.println("flatMap() onNext $it")
                }, {
                    System.out.println("flatMap() onError ${it.localizedMessage}")
                }, {
                    System.out.println("flatMap() onComplete")
                })
    }
}