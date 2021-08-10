package com.franzandel.moviesubmission.core.mapper

/**
 * Created by Franz Andel on 04/05/21.
 * Android Engineer
 */

abstract class BaseMapper<SourceType, ResultType> {
    abstract fun map(dataModel: SourceType): ResultType
}