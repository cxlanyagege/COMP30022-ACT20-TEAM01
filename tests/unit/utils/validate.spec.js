import { isExternal } from '@/utils/validate.js'

describe('Utils:validate', () => {
  it('isExternal', () => {
    expect(isExternal('http://localhost:8081')).toBe(true)
    expect(isExternal('/dashboard')).toBe(false)
    expect(isExternal('./dashboard')).toBe(false)
    expect(isExternal('dashboard')).toBe(false)
  })
})
