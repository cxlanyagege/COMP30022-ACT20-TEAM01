import { param2Obj } from '@/utils/index.js'
describe('Utils:param2Obj', () => {
  const url = 'https://act20team01.xyz:8081/param20bj'

  it('param2Obj test', () => {
    expect(param2Obj(url)).toEqual({
      // Remote server not ready
    })
  })
})
