package com.ekotech.breakingbad.data.local

import com.ekotech.breakingbad.data.local.dao.BreakingBadCharactersDao

interface BreakingBadDatabase {
    fun breakingBadCharactersDao(): BreakingBadCharactersDao
}