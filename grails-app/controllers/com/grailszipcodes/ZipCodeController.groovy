package com.grailszipcodes



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ZipCodeController {
    def ZipCodeService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        if (params.state){
            params.max = Math.min(max ?: 10, 100)
            respond ZipCode.findAllByState(params.state), model:[zipCodeInstanceCount: ZipCode.count()]
        }
        else {
          redirect(action:'tagCloud')
        }
    }

    def show(ZipCode zipCodeInstance) {
        respond zipCodeInstance
    }

    def resetCodes(){
        ZipCodeService.clearCodes()
        ZipCodeService.loadCodes()
        redirect(action: "index", params: params)
    }

    def tagCloud = {
        def zipCodes = ZipCode.getAll().countBy{ it.state }
        def tagCodes = zipCodes.sort{it.value}
        def tagValues = []
        tagCodes.each {
            tagValues.add(it.key)
        }
        [codes: zipCodes.sort{it.key},
         ranking: tagValues]

    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'zipCodeInstance.label', default: 'ZipCode'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
