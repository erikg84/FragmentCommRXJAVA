package com.batch.fragmentcommrxjava


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_top.*

/**
 * A simple [Fragment] subclass.
 */
class TopFragment : Fragment() {
    private val messages = ArrayList<String>()
    private var message:String=""

    private lateinit var messageAdapter: MessageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top, container, false)
    }

    override fun onResume() {
        super.onResume()
        sendBtn.setOnClickListener {
            val msg = msgBox.text.toString()
            if (msg.isNotEmpty() && !msg.contentEquals(message)){
                RxBus.instance?.botFragSubject?.onNext(msg)
                message = msg
                msgBox.setText("")
            }
        }


        messageAdapter = MessageAdapter(messages)

        val lManager = LinearLayoutManager(context).apply { reverseLayout = true }
        msgListA.apply {
            layoutManager = lManager
            adapter = messageAdapter
        }

        RxBus.instance?.topFragSubject?.subscribe(object :Observer<String>{
            override fun onComplete() {}

            override fun onSubscribe(d: Disposable) {}

            override fun onNext(msg: String) {
                messages.add(msg)
                messageAdapter.notifyItemInserted(messages.size)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }

        })
    }

}
