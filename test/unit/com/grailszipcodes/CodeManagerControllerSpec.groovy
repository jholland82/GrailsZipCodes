package com.grailszipcodes



import grails.test.mixin.*
import spock.lang.*

@TestFor(CodeManagerController)
@Mock(CodeManager)
class CodeManagerControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.codeManagerInstanceList
            model.codeManagerInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.codeManagerInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            def codeManager = new CodeManager()
            codeManager.validate()
            controller.save(codeManager)

        then:"The create view is rendered again with the correct model"
            model.codeManagerInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            codeManager = new CodeManager(params)

            controller.save(codeManager)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/codeManager/show/1'
            controller.flash.message != null
            CodeManager.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def codeManager = new CodeManager(params)
            controller.show(codeManager)

        then:"A model is populated containing the domain instance"
            model.codeManagerInstance == codeManager
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def codeManager = new CodeManager(params)
            controller.edit(codeManager)

        then:"A model is populated containing the domain instance"
            model.codeManagerInstance == codeManager
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            controller.update(null)

        then:"A 404 error is returned"
            status == 404

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def codeManager = new CodeManager()
            codeManager.validate()
            controller.update(codeManager)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.codeManagerInstance == codeManager

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            codeManager = new CodeManager(params).save(flush: true)
            controller.update(codeManager)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/codeManager/show/$codeManager.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            controller.delete(null)

        then:"A 404 is returned"
            status == 404

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def codeManager = new CodeManager(params).save(flush: true)

        then:"It exists"
            CodeManager.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(codeManager)

        then:"The instance is deleted"
            CodeManager.count() == 0
            response.redirectedUrl == '/codeManager/index'
            flash.message != null
    }
}
