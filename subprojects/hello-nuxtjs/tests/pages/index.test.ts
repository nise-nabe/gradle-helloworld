import { $fetch, setup } from '@nuxt/test-utils'
import { describe, test, expect } from 'vitest'

describe('browser', async () => {
    await setup({ browser: true })

    test('renders the index page', async () => {
        const html = await $fetch('/')

        expect(html).toContain('Home page')
    })
})
