package com.witty.bank.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations

fun <X, Y> LiveData<X>.map(mapper: (X) -> Y): LiveData<Y> =
    Transformations.map(this, mapper)

fun <X, Y> LiveData<X>.switchMap(mapper: (X) -> LiveData<Y>): LiveData<Y> =
    Transformations.switchMap(this, mapper)

fun <X> LiveData<X>.mergeWith(vararg otherLiveData: LiveData<X>): LiveData<X> =
    mergeLiveData(*otherLiveData.toMutableList().apply { add(this@mergeWith) }.toTypedArray())

private fun <X> mergeLiveData(
    vararg dataAndObservers: Pair<LiveData<X>, MediatorLiveData<X>.(X) -> Unit>
): LiveData<X> =
    MediatorLiveData<X>().apply {
        dataAndObservers.forEach {
            addSource(it.first) { nextValue ->
                it.second(this, nextValue)
                value = nextValue
            }
        }
    }

private fun <X> mergeLiveData(vararg listOfLd: LiveData<X>): LiveData<X> =
    MediatorLiveData<X>().apply {
        listOfLd.forEach { nextSource ->
            addSource(nextSource) { nextValue -> value = nextValue }
        }
    }

fun <A, B, R> LiveData<A>.combineLatest(b: LiveData<B>, mapper: (A, B) -> R): LiveData<R> =
    MediatorLiveData<R>().apply {
        var lastA: A? = null
        var lastB: B? = null

        addSource(this@combineLatest) { nextValueA ->
            lastA = nextValueA
            if (lastA == null && value != null) value = null
            if (lastA != null && lastB != null) value = mapper(lastA!!, lastB!!)
        }

        addSource(b) { nextValueB ->
            lastB = nextValueB
            if (lastB == null && value != null) value = null
            if (lastA != null && lastB != null) value = mapper(lastA!!, lastB!!)
        }
    }

fun <A, B, C, R> LiveData<A>.combineLatest(b: LiveData<B>,
                                           c: LiveData<C>,
                                           mapper: (A, B, C) -> R
): LiveData<R> =
    MediatorLiveData<R>().apply {
        var lastA: A? = null
        var lastB: B? = null
        var lastC: C? = null

        addSource(this@combineLatest) { nextValueA ->
            lastA = nextValueA
            if (lastA == null && value != null) value = null
            if (lastA != null && lastB != null && lastC != null) value =
                mapper(lastA!!, lastB!!, lastC!!)
        }

        addSource(b) { nextValueB ->
            lastB = nextValueB
            if (lastB == null && value != null) value = null
            if (lastA != null && lastB != null && lastC != null) value =
                mapper(lastA!!, lastB!!, lastC!!)
        }

        addSource(c) { nextValueC ->
            lastC = nextValueC
            if (lastC == null && value != null) value = null
            if (lastC != null && lastB != null && lastA != null) value =
                mapper(lastA!!, lastB!!, lastC!!)
        }
    }