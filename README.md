# MVVMKotlin
A boilerplate-less Kotlin MVVM

The idea is to develop a sample project with Kotlin using an MVVM architecture:

Key features:

- The codebase should require the less boilerplate code possible (All the examples around require a lot of wiring and It'd be cool to figure out a way to remove this)

- Using Databindings, RxJava, Retrofit with the Rx plugin and Room to implement the MVVM. All network responses should be written in the database and those responses should be forwarded to the ViewModel.

- The Repository needs to have the logic to trach the sources of the streams (possibly using a DataSource wrapper). So if we get a 200 response that should update the database and broadcast the response back to the ViewMode, but if we get a 304 we shouldn't waste time writing it into the database.

- Separate the project in modules, domain, repository and ui possibly.

- Unitesting should be really straightforward, just mocking the Repositories reposponses through mocked json files in the resource folder.

- Add the functionality to support offline posting and keep track of retries.

#Research

One question that I've trying to answer sice I started diggin in all this "Kotlin-MVVM" new wave was "It's really that helpful?". I mean, most of the projects that I've seen so far have a LOT of boilerplate code, It's like if you'd spent more time wiring all the components toghether than actually implementing the feature.

So let's check the classic MVP approach and see what we can improve over that with MVVM

![alt text](https://github.com/4gus71n/MVVMKotlin/blob/master/classic-mvp.png?raw=true)

In the classic MVP the View/UI (Activity+Fragment+View definition) communicates to the Presenter which communicates with the Interactors/UseCases to get the data from the model (Network/DB/Anywhere) and then it returns the resutl throught the view definition callback interface. Pretty straightforward.

![alt text](https://github.com/4gus71n/MVVMKotlin/blob/master/mvvm-approach.png?raw=true)

In the MVVM apporach the View communicates with the ViewModel, sending commands (`getUsers()`, `fetchRepositories()`, `doSomething()`, etc) and observes the ViewModel for any change that may happen (the list of users changed, the repositories were fetched, etc). The ViewModel communication with the Model layer is pretty much the same than the Presenter with the Model layer. I've seen many projects that are basically using the ViewModel as a super-Presenter of sorts. And I think that this is a really bad move, imagine that you have a UserListViewModel and you can observe a list of users from there, but then you need to implement an EliteUserListViewModel which only displays "elite" users. You're gonna strar mixing things up, having way too many variables in your ViewModel, having repeated logic, etc. I think that the Presenter layer should keep existing, but the ViewModel layer should be just a holder of the values that we want reflected back in the UI. So to wrap it up:

- In MVP the communication between the View layer and the Presenter layer is done through callbacks. The View requests something to the Presenter, the Presenter uses the Model to fetch the data and then it sends it back to the View through the View callback interface. But in MVVM the View requests something to the ViewModel and observes the ViewModel for any change. MVP callbacks, MVVM observables.

- In MVVM the ViewModel should be just a holder to store the data that we want to have reflected bakc in the UI, a `List<Users>` or a `List<Repository>` the stuff that we would normally sent back to the UI through callbacks in a MVP approach.

- In MVVM the View should communicate only with the ViewModel, the ViewModel should internally redirect the request to the Presenter which would use the Model to load the ViewModel variables.

- Don't smash toghether the Presenter and the ViewModel.






