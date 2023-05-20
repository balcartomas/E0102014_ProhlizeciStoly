package com.veba.e0102014_prohlizecistoly.domain.use_case.update.state

sealed class UpdateState {
    object Loading: UpdateState()
    object Updated: UpdateState()
    object NotUpdated: UpdateState()
    object NotOnWifi: UpdateState()
    object Error: UpdateState()
}
