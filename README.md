# MVVMKotlin (WIP)
A boilerplate-less Kotlin MVVM

The idea is to develop a sample project with Kotlin using an MVVM architecture:

Key features:

- The codebase should require the less boilerplate code possible (All the examples around require a lot of wiring and It'd be cool to figure out a way to remove this)

- Using Databindings, RxJava, Retrofit with the Rx plugin and Room to implement the MVVM. All network responses should be written in the database and those responses should be forwarded to the ViewModel.

- The Repository needs to have the logic to trach the sources of the streams (possibly using a DataSource wrapper). So if we get a 200 response that should update the database and broadcast the response back to the ViewMode, but if we get a 304 we shouldn't waste time writing it into the database.

- Separate the project in modules, domain, repository and ui possibly.

- Unitesting should be really straightforward, just mocking the Repositories reposponses through mocked json files in the resource folder.

- Add the functionality to support offline posting and keep track of retries.

# Research

One question that I've trying to answer since I started diggin in all this "Kotlin-MVVM" new wave was "It's really that helpful?". I mean, most of the projects that I've seen so far have a LOT of boilerplate code, It's like if you'd spent more time wiring all the components toghether than actually implementing the feature.

So let's check the classic MVP approach and see what we can improve over that with MVVM

![alt text](https://github.com/4gus71n/MVVMKotlin/blob/master/classic-mvp.png?raw=true)

In the classic MVP the View/UI (Activity+Fragment+View definition) communicates to the Presenter which communicates with the Interactors/UseCases to get the data from the model (Network/DB/Anywhere) and then it returns the resutl throught the view definition callback interface. Pretty straightforward.

![alt text](https://github.com/4gus71n/MVVMKotlin/blob/master/mvvm-approach.png?raw=true)

In the MVVM apporach the View communicates with the ViewModel, sending commands (`getUsers()`, `fetchRepositories()`, `doSomething()`, etc) and observes the ViewModel for any change that may happen (the list of users changed, the repositories were fetched, etc). The ViewModel communication with the Model layer is pretty much the same than the Presenter with the Model layer. I've seen many projects that are basically using the ViewModel as a super-Presenter of sorts. And I think that this is a really bad move, imagine that you have a UserListViewModel and you can observe a list of users from there, but then you need to implement an EliteUserListViewModel which only displays "elite" users. You're gonna strar mixing things up, having way too many variables in your ViewModel, having repeated logic, etc. I think that the Presenter layer should keep existing, but the ViewModel layer should be just a holder of the values that we want reflected back in the UI. So to wrap it up:

- In MVP the communication between the View layer and the Presenter layer is done through callbacks. The View requests something to the Presenter, the Presenter uses the Model to fetch the data and then it sends it back to the View through the View callback interface. But in MVVM the View requests something to the ViewModel and observes the ViewModel for any change. MVP callbacks, MVVM observables.

- In MVVM the ViewModel should be just a holder to store the data that we want to have reflected bakc in the UI, a `List<Users>` or a `List<Repository>` the stuff that we would normally sent back to the UI through callbacks in a MVP approach.

- In MVVM the View should communicate only with the ViewModel, the ViewModel should internally redirect the request to the Presenter which would use the Model to load the ViewModel variables.

- Don't smash toghether the Presenter and the ViewModel.

- One issue that I always had when working with MVP is "Where to store the model variables?". What I've always done was having those kind of variables (`List<User>`, `TeamDetail`, `HouseInfo`) in the Presenter. When initializing the Presenter and binding the View to the Presenter I used to pass along the Fragment's arguments and the Fragment's saveInstanceState, and from those two Bundles I'd take the info that I needed, but I needed to do a little bit of wiring in order to do that. Maybe with the ViewModels I can get rid of that issue.

# Test project

I've seen that all the examples using MVVM are just simple TODO list Apps or they show data in a TextView/ImageView. I think that these kind of examples are not fit to measure if an MVVM approach is good enough to use in a project. 

So the sample App that I'm gonna build is going to be an App that allows you to search for food recipes, we are going to have three screens. The main recipe list, a detail view that shows the neccesary ingredients, and a filter view that allows us to search and filter the criteria used to find recipes. These screens should inherit and reuse each other behaviour, so we can see if the MVVM apporach is truly the way to go.

# Captain's log

???: Okay I finished the DI, the ViewModelFactory is now hooked with Dagger so we can provide the different ViewModels from there, when creating a new screen we should only need three methods hooked in the #onCreate(). The call to inject the dependecies (which injects the ViewModelFactory) and the ViewModelFactory call to initialize the ViewModel.

4/1/2018: Of the 30000 ways to implement a RecyclerView's Adapter with MVVM I think that I figured out the right one. Usually the Adapter implementation in MVVM is splitted in two different ways of doing it. The first one is passing the ViewModel instance of the screen through the Adapter's constructur and using the Collection that we have stored there. This is done this way so if we remove an item from the adapter that change is reflected in the screen's ViewModel Collection as well. But I don't think that tying the Adapter to a specific ViewModel is a good idea. So I'm going with the second approach that is having a ViewModel for the Adapter's items. Is pretty straightforward and it separates the Adapter from the specific screen. 



