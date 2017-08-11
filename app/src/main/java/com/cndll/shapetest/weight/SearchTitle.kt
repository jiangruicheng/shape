package com.cndll.shapetest.weight

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import com.cndll.shapetest.R

/**
 * Created by jiangruicheng on 2017/8/11.
 */
class SearchTitle {
    lateinit var query: EditText
    lateinit var delete: ImageView

    fun init(view: View, queryEvent: (queryString: String) -> Boolean) {
        query = view.findViewById(R.id.search_edit) as EditText
        query.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        query.setInputType(EditorInfo.TYPE_CLASS_TEXT);
        delete = view.findViewById(R.id.delete) as ImageView
        delete.visibility = View.GONE
        delete.setOnClickListener {
            query.setText("")
            delete.visibility = View.GONE
        }
        query.setOnEditorActionListener { v, actionId, event -> queryEvent(query.text.toString()) }
        query.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null) {
                    if (s.length != 0) {
                        delete.visibility = View.VISIBLE
                    } else {
                        delete.visibility = View.GONE
                    }
                }

            }
        })
    }
}
