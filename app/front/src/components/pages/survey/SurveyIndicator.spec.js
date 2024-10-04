import SurveyIndicator from './SurveyIndicator.vue'
import { describe, it, expect, afterEach } from 'vitest'
import { cleanup, render, screen } from '@testing-library/vue'


describe('SurveyIndicatorPage', () => {

  beforeEach(() => {
    render(SurveyIndicator)
  })
  afterEach(cleanup)
  
    it('計算式ボタン押下されたらヘルプコンポーネント表示フラグをONにする', async () => {

      const button = screen.getByRole('button', { name: '計算式' });

      //TODO 表示フラグ取得

      
      await fireEvent.click(button);

    })




  })