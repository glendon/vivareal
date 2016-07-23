package vivareal.integration.model

import vivareal.domain.Provincy

class ProvincyJson {

	def name
	def boundaries	

	def createProvincy() {
		def provincy = new Provincy()
		provincy.name = this.name
		provincy.upperLeftX = this.boundaries.upperLeft.x
		provincy.upperLeftY = this.boundaries.upperLeft.y
		provincy.bottomRightX = this.boundaries.bottomRight.x
		provincy.bottomRightY = this.boundaries.bottomRight.y

		provincy
	}

}
