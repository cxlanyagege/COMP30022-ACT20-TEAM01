import { validUsername, isExternal } from '@/utils/validate.js'

describe('Utils:validate', () => {
  it('isExternal', () => {
    expect(isExternal('https://act20team01.xyz:8081')).toBe(true)
    expect(isExternal('/dashboard')).toBe(false)
    expect(isExternal('./dashboard')).toBe(false)
    expect(isExternal('dashboard')).toBe(false)
  })
})
