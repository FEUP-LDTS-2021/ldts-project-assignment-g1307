package model.menu

import spock.lang.Specification

class MenuModelTest extends Specification {
    def "SetNextOption"() { // This tests will change as we change the options enum ... OCP violation again
        given:
        MenuModel menuModel = new MenuModel()
        when:
        menuModel.setNextOption()
        then:
        menuModel.getCurrentOption() == MenuModel.Option.BLITZ
    }

    def "SetPreviousOption"() {
        given:
        MenuModel menuModel = new MenuModel()
        when:
        menuModel.setPreviousOption()
        then:
        menuModel.getCurrentOption() == MenuModel.Option.EXIT
    }

    def "Option diff to another"() {
        given:
        MenuModel.Option option = MenuModel.Option.BULLET;
        when:
        int i = option.diffToOption(MenuModel.Option.EXIT)
        then:
        i == 4
    }

    def "max Option"() {
        expect:
        MenuModel.Option.maxLength() == 9
    }
}
