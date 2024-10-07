package pl.dziewulskij.luxmedinterviewapp

import org.springframework.beans.factory.annotation.Autowired

class LuxmedInterviewAppApplicationTests extends AbstractBaseIT {

    @Autowired
    LuxmedInterviewAppApplication luxmedInterviewAppApplication

    def 'should load context'() {
        expect:
        luxmedInterviewAppApplication
    }

}
