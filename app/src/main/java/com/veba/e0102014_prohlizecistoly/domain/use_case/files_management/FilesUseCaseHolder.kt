package com.veba.e0102014_prohlizecistoly.domain.use_case.files_management

import com.veba.e0102014_prohlizecistoly.domain.use_case.files_management.DeleteFileUC
import com.veba.e0102014_prohlizecistoly.domain.use_case.files_management.GetJsonFromFileUC
import com.veba.e0102014_prohlizecistoly.domain.use_case.files_management.SaveFileUC
import com.veba.e0102014_prohlizecistoly.domain.use_case.files_management.UnpackZipFileUC
import javax.inject.Inject

class FilesUseCaseHolder @Inject constructor(
    val saveFileUC: SaveFileUC,
    val deleteFileUC: DeleteFileUC,
    val getJsonFromFileUC: GetJsonFromFileUC,
    val unpackZipFileUC: UnpackZipFileUC
) {
}