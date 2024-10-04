import { SectionA } from "./components/sections/sectionA"
import { SectionB } from "./components/sections/sectionB"
import { SectionC } from "./components/sections/sectionC"
import { SectionD } from "./components/sections/sectionD"
import { SectionE } from "./components/sections/sectionE"

export default function Landing() {
  return (
    <>
      <main className="flex-1">
        <SectionA />
        <SectionB />
        <SectionC />
        <SectionD />
        <SectionE />
      </main>
    </>
  )
}