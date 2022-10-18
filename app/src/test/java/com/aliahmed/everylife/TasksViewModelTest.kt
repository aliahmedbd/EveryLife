package com.aliahmed.everylife

import com.aliahmed.everylife.model.Events
import com.aliahmed.everylife.repository.TasksRepository
import com.aliahmed.everylife.viewmodel.TasksViewModel
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class TasksViewModelTest {

    private lateinit var viewModel: TasksViewModel
    private lateinit var tasks : List<Events>
    private var filterList: MutableList<String> =
        mutableListOf("general", "medication", "hydration")
    private val tasksJson = "[\n" +
            "  {\n" +
            "    \"id\": 1,\n" +
            "    \"name\": \"Take the rubbish out\",\n" +
            "    \"description\": \"Empty the bin and take the rubbish and recycling to the communal rubbish bins that are on the lower ground floor of the building\",\n" +
            "    \"type\": \"general\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": 2,\n" +
            "    \"name\": \"Make a hot drink\",\n" +
            "    \"description\": \"Make David a cup of tea with full fat milk  and no sugar.  David likes to have his tea medium strength\",\n" +
            "    \"type\": \"hydration\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": 3,\n" +
            "    \"name\": \"5 ml Azopt 10mg/1ml\",\n" +
            "    \"description\": \"Instil one drop to both eyes at the morning. Put on by HM checked by VH. This is now only to be put in in the morning as the private carer will instil at lunch time\",\n" +
            "    \"type\": \"medication\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": 4,\n" +
            "    \"name\": \"Asprin\",\n" +
            "    \"description\": \"This is dispersible and should be dissolved in water and administered with or just after food.\",\n" +
            "    \"type\": \"medication\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": 5,\n" +
            "    \"name\": \"Make a snack\",\n" +
            "    \"description\": \"Soup, or biscuits or both. David also likes Advocate with salt on.  Request from David's son not to make any other food as David is not eating it and it is then left out overnight and attracting mice.\",\n" +
            "    \"type\": \"nutrition\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": 6,\n" +
            "    \"name\": \"Eyelid hygiene\",\n" +
            "    \"description\": \"The eyelids should be washed with a cotton bud dipped into a mixture of 1 part baby shampoo and 4 parts water. Linda is going to ensure that the cotton buds and baby shampoo are available. The care worker should wipe the outside of the eyelids with the cotton bud.\",\n" +
            "    \"type\": \"general\"\n" +
            "  }\n" +
            "]"


    @Before
    fun initialize() {
        viewModel = TasksViewModel(null)
    }

    @Test
    fun tasksListFilterSizeTest() {
        val gson = GsonBuilder().create()
        val tasks = gson.fromJson<List<Events>>(tasksJson, object :
            TypeToken<List<Events>>(){}.type)
        val size = viewModel.filterData(tasks,filterList).size
        Assert.assertEquals(5, size)
    }
}