package com.batch.fragmentcommrxjava

import io.reactivex.subjects.PublishSubject

class RxBus {


    val botFragSubject = PublishSubject.create<String>()
    val topFragSubject = PublishSubject.create<String>()



    companion object{
        private var rxInstance:RxBus? = null
        val instance:RxBus?
        get(){
            if(rxInstance == null){
                rxInstance = RxBus()
            }
            return rxInstance
        }
    }
}