# App Components
## Introduction
When building an app in Android, you're building it from components.
These components provide a lot of __reusability__, so that you don't have to reinvent the wheel everytime.
The whole android ecosystem consists of components.

__Pros:__
- Reusability -> As a developer, you can reuse already existing components in your code
- Modularity  -> If you want to change the functionality of a component, you know exactly, what to change.
Also you don't break something else in the process, since you're only changing the functionality of that component
- Separation Of Concerns -> The components are only doing one thing.
They are therefore much simpler, to document and also to understand.

__Cons:__
- The component system can be a bit overkill for a simple app.
- The modular approach is hard to grasp at the beginning. Which can put off some junior devs.

## Examples
Here are some components, which you want to use in your application

### Intents
- An Intent is a Messaging Object to Request Action from another app component
- Start an activity by passing an intent to startActivity()
- Explicit intents -> call class / activity by ID
- Implicit intent -> request an action which the responsible component then execute
- It is also possbile, listen for system-wide-intents (broadcasts). E.g.: ACTION_BATTERY_LOW

#### Code example
```java
// Example, which changes the view
public class MainActivit() {
  // The MainActivity class is the view, which is presented at the beginning of the program
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // The intent should not be defined in the button event since 'this' as the first param does not work then
    Intent changeActivity = new Intent(this, ExcitingNewActivity.class);
    button.setOnClickListener(new View.OnClickListener() {
      // When a specific button is pressed, a new view should appear
      @Override
      public void onClick(View view) {
        startActivity(changeActivity);
        // After that, the new view is visible
      }
    });
  }
}
```

### Activity
- Represents a single screen in the UI
- It consists of the controller class (e.g.: MainActivity.java) and the underlying xml.
  - __The xml:__ Acts as a stylesheet. When you edit one in Android Studio, a graphical interface opens
  which lets you edit the xml in a more comfortable manner. A button for example, looks like
  this under the hood:
  ```xml
  <Button
    android:id="@+id/exampleButton"
    ...
    android:layout_marginBottom="15dp"
    android:onClick="handleButton"
    android:text="Button"
  />
  ```
  - __The controller class:__ Handles your button click events and all that

### Fragment
- A fragment is sort of a sub activity
- Use Case: The navigation-drawer which you can make visbile by swiping from the left border
  out in the Play Store for example, is a fragment

### Loaders
- Loads data on seperate threads from UI to make the app look smoother
- Loaders can implement observers to notify the host process, when the data is loaded
- __Advantages over thread:__
  - They cache results to avoid duplicate queries
  - Callback methods for event
  - You don't have to manage the thread yourself. Look at a loader as a sort of automated
    threads.


### Content Providers
- Manages Access to data storage (eg calendar api, App internal sqlite Database or cloud service)
- Called API in other domains
- If you wanted to get the calendar events in your app, you'd have to call the calendar Provider.
  For you to do that, you'd have to put the line
  ```xml
  <uses-permission android:name="android.permission.READ_CALENDAR"/>
  ```
  In the manifest of your app. After that, you can get the calendar entries.
  You can find further information [here](https://developer.android.com/guide/topics/providers/calendar-provider.html)
- __Pros:__
  - With this approach it is much easier to migrate to another content provider. Since you
    only have to change the logic for the component, which talks to the DB, the cloud...
  - Seperation of concerns: The UI only needs to render the data. Database calls etc are all
    made in a central, well organized area

