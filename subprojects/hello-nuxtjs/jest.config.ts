import {Config} from '@jest/types'

export default async (): Promise<Config.InitialOptions> => {
    return {
        preset: '@nuxt/test-utils'
    }
}
