# com.e2e.innosabi

This is a simple end to end testing prototype for innosabi testing challenge

All the tests are available in com.e2e.innosabi.E2E_protoType class

Setup & Run
-----
* **Prerequisites:**
    * Install the latest version of [Java](https://java.com) and [Maven](https://maven.apache.org/download.html).
    * Install [Eclipse](http://www.eclipse.org/downloads/), the [Maven plugin](http://eclipse.org/m2e/), and optionally the [GitHub plugin](http://eclipse.github.com/).

* **Compile**
```bash
# Compile and download libraries
mvn compile install
```
* **Run Tests**
   * Right click on src/main/java/com.e2e.innosabi E2E_protoType.java
   * Run As > TestNG Test

Results
-----
* **TestNG  View**
![Test NG Result](https://github.com/binduMashal/com.e2e.innosabi/blob/main/testNgRes.PNG)

* **Command Line Output:**
```
[RemoteTestNG] detected TestNG version 7.4.0
You are testing in firefox
1621873215428	geckodriver	INFO	Listening on 127.0.0.1:15531
1621873216726	mozrunner::runner	INFO	Running command: "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe" "--marionette" "-foreground" "-no-remote" "-profile" "C:\\Users\\Pradeep\\AppData\\Local\\Temp\\rust_mozprofilernpHDj"
1621873218745	Marionette	INFO	Marionette enabled
console.warn: SearchSettings: "get: No settings file exists, new profile?" (new Error("", "(unknown module)"))
1621873233652	Marionette	INFO	Listening on port 51700
1621873234182	Marionette	WARN	TLS certificate errors will be ignored for this session
May 24, 2021 6:20:34 PM org.openqa.selenium.remote.ProtocolHandshake createSession
INFO: Detected dialect: W3C
/******************************************************************
/* We will perform below tests sequentially:                      */
/*   1. Check if URL is openened Correctly                        */
/*   2. Enter User name and navigate to password page        	  */
/*   3. Enter Password and check if it is navigated to home page  */
/*   4. Search and Click Innovation Challenge                     */
/*   5. Submit New idea and verfiy                                */
/*   6. Add comment to another Idea and verify                    */
/* Tech Stack Used:                                               */
/*    1. Java                                                     */
/*    2. Maven                                                    */
/*    3. Selenium web drivers                                     */
/*    4. TestNg                                                   */
/******************************************************************/
Opening E2E test URL and verifying Test started
Opening E2E test URL and verifying Test Completed
-----------------------
Verifying login Page Title Test started
Verifying login Page Title Test Completed
-----------------------
Verifying if navigated to password Page Test started
Verifying if navigated to password Page Test Completed
-----------------------
Verifying if navigated to Home Page after authentication Test started
Verifying if navigated to Home Page after authentication Test Completed
-----------------------
Find & select Innovation Challenge Test started
Find & select Innovation Challenge starte Test Completed
-----------------------
Add Idea & Verfiy Test Started
Inserting Idea with title: "this is my idea with ID:7a706b48-5ca1-455e-af1a-9bb008a6a4c6"
Found newly added Idea
Add Idea & Verfiy Test Completed
-----------------------
Add Comment & Verfiy Test Started
adding comment: "this is my comment with unique ID:9a916ef2-3bb0-461c-a09e-2329ca4016af"
Found newly added comment
Add Comment & Verfiy Test Completed
-----------------------
PASSED: verifyLoginPageTitle
PASSED: findAndClickInnovationChallenge
PASSED: addCommentAndVerify
PASSED: verifyIfNavigatedToPasswordPage
PASSED: verifyIfUrlIsOpened
PASSED: verifyIfNavigatedToHomePage
PASSED: addNewIdeaAndVerify

===============================================
    Default test
    Tests run: 7, Failures: 0, Skips: 0
===============================================
```
