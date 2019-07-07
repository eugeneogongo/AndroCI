[![License: MIT](https://img.shields.io/badge/License-MIT-Green.svg)](https://opensource.org/licenses/MIT) [![Build Status](https://travis-ci.org/siddharth2010/AndroCI.svg?branch=master)](https://travis-ci.org/siddharth2010/AndroCI)

# AndroCI

A beautiful Travis CI client!

## Screenshots:

| GitHub OAuth | Repo List | The Navbar
|:-------------:|:-------------:|:-------------:
| ![GitHub OAuth](https://github.com/siddharth2010/Android-Build-Client/blob/master/screenshots/authorization.png)|![Repo List](https://github.com/siddharth2010/Android-Build-Client/blob/master/screenshots/repo_list.png)|![The Navbar](https://github.com/siddharth2010/Android-Build-Client/blob/master/screenshots/navbar.png)|

_AndroCI is under active development. More features are on their way!_

## Usage

### Setup

#### Prerequisites
- To work on this repo on your local environment, you would need to generate register your flavour of this app with GitHub in order to access the GitHub OAuth API.
  - Please follow the steps [here](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/), in order to set up your credentials for using this app.

#### Setting up the project in Android Studio
- Once you are done with this, create a file with the name "dubug_gradle.properties", and paste the following code:
```groovy
github_client_id=<Client ID Generated in Step 1>
github_secret=<Secret ID Generated in Step 1>
redirect_url=androci://callback
```
- Change the `Build Variants` of both the main App and the APICommunicator Library to `debug`.
- Now, just rebuild the project in android studio, and it should work.

### Package Structure
The app is divided into two Components:
- The `APICommunicator` Library, which provides an interface to make API Calls and Authorization Requests via GitHub and Travis APIs.
- The `AndroCI` app, which is basically a front end, and has the `APICommunicator` Library as a dependency.


| APICommunicator | AndroCI 
|:-------------:|:-------------:
| ![APICommunicator](https://github.com/siddharth2010/Android-Build-Client/blob/master/screenshots/rest_services.png) | ![AndroCI](https://github.com/siddharth2010/Android-Build-Client/blob/master/screenshots/front_end.png)


## Libraries in Use
- [**Retrofit**](https://github.com/square/retrofit) for constructing the REST API
- [**Picasso**](https://github.com/square/picasso) for loading images
- [**Gson**](https://github.com/google/gson) to convert Java objects to JSON and vice-versa
- [**Gson Converter**](https://github.com/square/retrofit/tree/master/retrofit-converters/gson) for serialization to and from JSON
