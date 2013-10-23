package com.grailszipcodes



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ZipCodeController {

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

    def create() {
        respond new ZipCode(params)
    }

    @Transactional
    def save(ZipCode zipCodeInstance) {
        if (zipCodeInstance == null) {
            notFound()
            return
        }

        if (zipCodeInstance.hasErrors()) {
            respond zipCodeInstance.errors, view:'create'
            return
        }

        zipCodeInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'zipCodeInstance.label', default: 'ZipCode'), zipCodeInstance.id])
                redirect zipCodeInstance
            }
            '*' { respond zipCodeInstance, [status: CREATED] }
        }
    }

    def edit(ZipCode zipCodeInstance) {
        respond zipCodeInstance
    }

    @Transactional
    def update(ZipCode zipCodeInstance) {
        if (zipCodeInstance == null) {
            notFound()
            return
        }

        if (zipCodeInstance.hasErrors()) {
            respond zipCodeInstance.errors, view:'edit'
            return
        }

        zipCodeInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ZipCode.label', default: 'ZipCode'), zipCodeInstance.id])
                redirect zipCodeInstance
            }
            '*'{ respond zipCodeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(ZipCode zipCodeInstance) {

        if (zipCodeInstance == null) {
            notFound()
            return
        }

        zipCodeInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ZipCode.label', default: 'ZipCode'), zipCodeInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    def loadCodes(){
        def states = ['AL', 'AK', 'AZ', 'AR', 'CA', 'CO', 'CT', 'DE', 'FL', 'GA',
                'HI', 'ID', 'IL', 'IN', 'IA', 'KS', 'KY', 'LA', 'ME', 'MD',
                'MA', 'MI', 'MN', 'MS', 'MO', 'MT', 'NE', 'NV', 'NH', 'NJ',
                'NM', 'NY', 'NC', 'ND', 'OH', 'OK', 'OR', 'PA', 'RI', 'SC',
                'SD', 'TN', 'TX', 'UT', 'VT', 'VA', 'WA', 'WV', 'WI', 'WY']
        states.each { state ->
            withRest(uri: 'http://api.geonames.org') {
                def http = get(path: '/postalCodeSearch', query:[placename:state, country:'US', username:'jholland']) { resp, xml ->
                    xml.code.each {
                        def code = new ZipCode(postalCode:it.postalcode.text(), name:it.name.text(), stateCode:it.adminCode1.text(), state:it.adminName1.text())
                        code.save()
                    }
                }
            }
        }
        redirect(action: "index", params: params)
    }

    def clearCodes(){
        ZipCode.executeUpdate("delete ZipCode")
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
