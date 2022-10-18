# EveryLife
 
This is a technical task from EveryLife Technology. I have names this app is "EveryLife" and this is a Kotlin first project and I have followed the advanced Android development using MVVM design pattern, Dependancy injection (Koin), Kotlin Coroutine, Kotlin Flows etc.

## App Details

Here is the recorded screen for the entire app journey:

![EveryLife](https://user-images.githubusercontent.com/11981999/196399754-0ee2ddad-5e32-4109-8d12-59c66eb9b7ff.gif)

### Features
1. Fetch the task list from API
2. App can fetch data if no active internet connectivity
3. Using bottom menu user can filter the tasks list

The tools I have used to gain the MVVM architecture pattern:

- MVVM :  MVVM architecure is followed for the code boilerplate. Where View, ViewModel, Repisitory are clearly used for maintailed the SOLID principle. (https://www.geeksforgeeks.org/mvvm-model-view-viewmodel-architecture-pattern-in-android/)
- Kotlin (https://kotlinlang.org/)
- Coroutine : To reduce the main thread task we can divide the task in many thread asychronously using the Kotlin Coroutine using viewmodel and lifecycle scope. (https://developer.android.com/kotlin/coroutines)
- Koin : KOIN is a ligh weight dependency injection which is written in pure Kotlin. Its really easy to learn and there is no generated code will be in KOIN used Android project. (https://insert-koin.io/)
- Kotlin Flows : In coroutines, a flow is a type that can emit multiple values sequentially, as opposed to suspend functions that return only a single value. (https://developer.android.com/kotlin/flow)
- Retrofit : (https://square.github.io/retrofit/)
- ROOM : Room database used for offline data management.
- BuildTypes: Build types used for defines the different build types like Development, Release and Stages
- Data Binding: ViewBinding used for the view initialization

### Unit test

For unit test I used Junit, Mockito and Espresso, where I tried two ways Unit test. For recycler view items I used instrumented test cases and for viewmodel test I use normal Junit test cases.

![Screenshot 2022-10-18 at 11 32 33](https://user-images.githubusercontent.com/11981999/196406960-1827421d-2658-4a8a-8ce4-73cfa922786e.png)


#### API Specification

There is a public api which is: https://adam-deleteme.s3.amazonaws.com/tasks.json
Here is the response:

```
[{
		"id": 1,
		"name": "Take the rubbish out",
		"description": "Empty the bin and take the rubbish and recycling to the communal rubbish bins that are on the lower ground floor of the building",
		"type": "general"
	},
	{
		"id": 2,
		"name": "Make a hot drink",
		"description": "Make David a cup of tea with full fat milk  and no sugar.  David likes to have his tea medium strength",
		"type": "hydration"
	},
	{
		"id": 3,
		"name": "5 ml Azopt 10mg/1ml",
		"description": "Instil one drop to both eyes at the morning. Put on by HM checked by VH. This is now only to be put in in the morning as the private carer will instil at lunch time",
		"type": "medication"
	},
	{
		"id": 4,
		"name": "Asprin",
		"description": "This is dispersible and should be dissolved in water and administered with or just after food.",
		"type": "medication"
	},
	{
		"id": 5,
		"name": "Make a snack",
		"description": "Soup, or biscuits or both. David also likes Advocate with salt on.  Request from David's son not to make any other food as David is not eating it and it is then left out overnight and attracting mice.",
		"type": "nutrition"
	},
	{
		"id": 6,
		"name": "Eyelid hygiene",
		"description": "The eyelids should be washed with a cotton bud dipped into a mixture of 1 part baby shampoo and 4 parts water. Linda is going to ensure that the cotton buds and baby shampoo are available. The care worker should wipe the outside of the eyelids with the cotton bud.",
		"type": "general"
	}
]
```
