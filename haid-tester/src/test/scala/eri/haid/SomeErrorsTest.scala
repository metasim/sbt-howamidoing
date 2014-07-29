package eri.haid


class SomeErrorsTest extends HaidTestSpec {
    describe("something that has some errors") {
        it("has no errors here") {
            assert(true)
        }

        it("has an errror here") {
            assert(false)
        }
    }
}


