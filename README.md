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
