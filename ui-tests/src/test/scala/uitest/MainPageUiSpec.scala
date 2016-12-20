package uitest

import com.acromancer.acromancer.version.BuildInfo._

class MainPageUiSpec extends BaseUiSpec {

  test("application version") {
    // when
    mainPage.open()

    // then
    mainPage.getVersionString should be (s"Build ${buildSha.substring(0, 6)}, $buildDate")
  }
}
