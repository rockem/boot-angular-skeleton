package features

import cucumber.api.groovy.EN
import cucumber.api.groovy.Hooks

import static cucumber.api.groovy.EN.Then

this.metaClass.mixin(Hooks)
this.metaClass.mixin(EN)

World {
    new MyWorld()
}

Before {
    browser.startBrowser('http://localhost:8888/app')
}

After {
    browser.quitBrowser()
}

Given(~/^I am on the home page$/) { ->
    browser.gotoUrl('/')
}

Then(~/^I should see "(.*?)"$/) { String value ->
    assert(browser.getPageSource().contains(value))
}