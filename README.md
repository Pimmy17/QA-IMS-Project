Coverage: 57.6%
# QA IMS Project
This project is an Inventory Management System that allows the creation, deletion and updating of customers, items and customer's orders.

## Getting Started

To get this project working on your own system follow the below instructions:

### Prerequisites

What things you need to install the software and how to install them:


*Terminal:
Powershell - Is already installed in most Computers
Git & GitBash - Installation can be found [here](https://git-scm.com/downloads)

*IDE:
[Eclipse](https://www.eclipse.org/downloads/) - Recommended
[VS Code](https://code.visualstudio.com/download) - Will require additional add-ons installing within the IDE in order to run Java files properly



### Installing


*Cloning the Repo:
If you have access to a terminal and an IDE then you should be able to clone the repo to allow you to run the application or if you fork and clone the repo, then you can make your own changes and version of the IMS.

    Firstly, head to my main page of the repo by [clicking here](https://github.com/Pimmy17/QA-IMS-Project).
    
    Secondly, click the green 'Code' button, which will present a drop down with the HTTPS and SSH key need to clone the repo.
    
    Thirdly, head to your terminal and the folder you wish to copy this repo in to and then type in 'git clone' followed by either the HTTPS or SSH key.
        Then press enter to begin the cloning
    
    Finally, to access the repo do the following depending on which of the above IDEs you decided on:
    ```
        For Eclipse: Open up the project by selecting -> 'File' ---> 'Open Projects From File System' ---> Select 'Directory' 
        and choose the file where the IMS project is stored on your system.
        For VS Code: Whilst in your terminal, type in 'cd QA-IMS-Project' to enter the project file, then type 'code .'
        This should open up VS Code and the project.
    ```

### To Run The Program:


In your terminal, enter in 'cd target'
Inside the target file, you can enter in 
```
'java -jar .\ims-0.0.1-jar-with-dependencies.jar'
```
then hit enter
This will run the program


An example of what can be expected from running the program can be found in the presentations file, under the slides named 'Run-through of Project'.

## Running the tests

(Instructions are for Eclipse)
If you wish to see the testing currently being used in this program, head to your IDE, then right click on the project name to reveal a drop down, 
which should contain an option 'Coverage As', click on this and select 'JUnit Test', this will show the not only the tests running, which will show
them passing (or failing if you are making your own edits through TDD) as well as the coverage the tests have within the project. Lines in the code
that are highlighted in green show they have covered by a working and passing test.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **[Chris Perrins](https://github.com/christophperrins)** - *Initial work*
* **[Andy Pimlott](https://github.com/Pimmy17)** - *Additional work*

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Ed Reynolds & Jordan Harrison @ QA Ltd.
* PG Tips for fuelling me in the mornings.
