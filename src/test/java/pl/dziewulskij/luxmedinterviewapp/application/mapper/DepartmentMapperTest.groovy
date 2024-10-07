package pl.dziewulskij.luxmedinterviewapp.application.mapper

import spock.lang.Specification

import java.util.stream.Collectors

class DepartmentMapperTest extends Specification {

    def 'should map ids to set of Department'() {
        given:
        def ids = [10L, 20L].toSet()

        when:
        def result = DepartmentMapper.toDepartmentSet(ids)

        then:
        verifyAll {
            result.size() == 2
            result.stream().map { d -> d.id }.collect(Collectors.toSet()) == ids
        }
    }

    def 'should map ids to set of Department when collection is null'() {
        expect:
        DepartmentMapper.toDepartmentSet(null).size() == 0
    }

}
