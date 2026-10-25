# Task: Planner 5D Editor Test Automation

Write UI and API tests that verify that clicking sofa item in Planner 5D editor works correctly.

## What you need to do:
1. Write UI test which would check that clicking on any sofa in Planner 5D editor catalog adds the sofa item to the project.
2. Write UI test which would test that the added sofa is visible in Planner 5D editor 3D mode.
3. Write API test which would test that calling the get project data/json API after 10 seconds will return correct project data/json containing the sofa.
4. If you have some more good ideas what should be tested feel free to add those also.

## Requirements
- Use Kotlin (or Java if you don't know Kotlin) for writing tests.
- Use at least once Kotlin coroutines (if writing in Kotlin) or virtual threads (if writing in Java).
- Provide the task results as a git repository packaged in a gitbundle and do not post it on public code sharing services (github, bitbucket, ...) - include the tests and the iOS app.
- We expect to be able to run your code without fuss - so if there are any specifics that are needed to get your code running, please commit them to git repository if possible.

## What we will look at
- Clarity, elegance and maintainability of code.
- Code consistency.

---

## Authentication Strategy

Planner5D uses an Email OTP (One-Time Password) system for authentication.
To avoid over-engineering the test framework with third-party email APIs for this assignment, I implemented session hijacking via cookie injection.

**To run the tests successfully:**
1. Log in to your Planner5D account manually in your local browser.
2. Open DevTools (F12) -> Application -> Cookies.
3. Copy the fresh values of `p5dvisitor` and `PHPSESSID`.
4. Paste them into your configuration file (e.g., `src/test/resources/config.properties`):
   ```properties
   cookie.visitor=YOUR_FRESH_VISITOR_TOKEN
   cookie.session=YOUR_FRESH_PHPSESSID

## Project Configuration

By default, the test framework attempts to open a workspace project named `TestProject`. If you are running the tests on a different account where the default project has a different name (e.g., "My Project" or "First Project"), the test will fail to find the element.

To fix this, update the `project.name` property in `src/test/resources/config.properties` to match the exact name of the project in your account:

```properties
project.name=Your Project Name