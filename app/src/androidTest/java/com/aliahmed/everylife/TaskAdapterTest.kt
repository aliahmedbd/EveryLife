package com.aliahmed.everylife

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.platform.app.InstrumentationRegistry
import com.aliahmed.everylife.adapter.TaskListAdapter
import com.aliahmed.everylife.model.Events
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class TaskAdapterTest {

    private lateinit var context: Context

    @Before
    @Throws(Exception::class)
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun postsRecycleViewHomeAdapter() {
        // Set up dummy data
        val tasks: List<Events> = listOf(
            Events(0, "test1", "test description 1", "general"),
            Events(1,  "test2", "test description 2", "medication"),
            Events(2,  "test3", "test description 3", "hydration")
        )
        val adapter = TaskListAdapter(tasks)
        val rvParent = context?.let { RecyclerView(it) }
        rvParent?.layoutManager = LinearLayoutManager(context)

        // Run test On onBindViewHolder and ViewHolder on dummy data
        val viewHolder: TaskListAdapter.ViewHolder =
            rvParent?.let { adapter.onCreateViewHolder(it, 0) }!!

        //Test number 1
        adapter.onBindViewHolder(viewHolder, 0)
        assertEquals("test1", viewHolder.txtHeading.text)

        //Test number 2
        adapter.onBindViewHolder(viewHolder, 2)
        assertEquals("test description 3", viewHolder.txtDescription.text)

    }

}