package com.grailszipcodes



import grails.test.mixin.*
import spock.lang.*

@TestFor(ZipCodeController)
@Mock(ZipCode)
class ZipCodeControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }


    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def zipCode = new ZipCode(params)
            controller.show(zipCode)

        then:"A model is populated containing the domain instance"
            model.zipCodeInstance == zipCode
    }
}
