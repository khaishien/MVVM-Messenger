package com.nexus.mvvmmessenger.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateUtils {
    companion object {

        fun formatStringToDate(
            format: SimpleDateFormat,
            apiDate: String?
        ): Date? {
            if (apiDate == null) {
                return null
            }
            try {
                return format.parse(apiDate)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return null
        }

        fun formatDateToString(
            format: SimpleDateFormat,
            date: Date?
        ): String? {
            return if (date == null) {
                null
            } else format.format(date)
        }

        fun formatDate(
            fromFormat: SimpleDateFormat,
            toFormat: SimpleDateFormat,
            date: String?
        ): String? {
            if (date == null) {
                return null
            }
            val parsedDate = formatStringToDate(fromFormat, date)
            return formatDateToString(toFormat, parsedDate)
        }

        var fullDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.UK)
        var readableDateFormat = SimpleDateFormat("dd MMM yyyy", Locale.UK)
        var readableTimeFormat = SimpleDateFormat("hh:mm a", Locale.UK)
    }
}