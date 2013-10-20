package com.grailszipcodes



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CodeManagerController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond CodeManager.list(params), model:[codeManagerInstanceCount: CodeManager.count()]
    }

    def show(CodeManager codeManagerInstance) {
        respond codeManagerInstance
    }

    def create() {
        respond new CodeManager(params)
    }

    @Transactional
    def save(CodeManager codeManagerInstance) {
        if (codeManagerInstance == null) {
            notFound()
            return
        }

        if (codeManagerInstance.hasErrors()) {
            respond codeManagerInstance.errors, view:'create'
            return
        }

        codeManagerInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'codeManagerInstance.label', default: 'CodeManager'), codeManagerInstance.id])
                redirect codeManagerInstance
            }
            '*' { respond codeManagerInstance, [status: CREATED] }
        }
    }

    def edit(CodeManager codeManagerInstance) {
        respond codeManagerInstance
    }

    @Transactional
    def update(CodeManager codeManagerInstance) {
        if (codeManagerInstance == null) {
            notFound()
            return
        }

        if (codeManagerInstance.hasErrors()) {
            respond codeManagerInstance.errors, view:'edit'
            return
        }

        codeManagerInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'CodeManager.label', default: 'CodeManager'), codeManagerInstance.id])
                redirect codeManagerInstance
            }
            '*'{ respond codeManagerInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(CodeManager codeManagerInstance) {

        if (codeManagerInstance == null) {
            notFound()
            return
        }

        codeManagerInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'CodeManager.label', default: 'CodeManager'), codeManagerInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'codeManagerInstance.label', default: 'CodeManager'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
