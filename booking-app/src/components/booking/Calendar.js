import DatePicker from "react-datepicker";
import 'react-datepicker/dist/react-datepicker.css';
import React, { useState } from "react"
import { es } from 'date-fns/locale'
import "../../styles/components/_calendar.scss"
import { addDays } from "date-fns"
import { useFetch } from "../../hooks/useFetch";
import { useParams } from "react-router-dom";

function Calendar({ startDate, endDate, setReservationDate }) {

  const { id } = useParams();

  const [data] = useFetch(
    `http://grupo6c1621back-env.eba-b34jmgbs.us-east-2.elasticbeanstalk.com/bookings/product/${id}/bookingDates`
  );

  const onChange = (dates) => {
    const [start, end] = dates;
    setReservationDate({
      startDate: start,
      endDate: end
    })
  };

  return (
    <DatePicker
      className="search__input"
      popperPlacement="auto"
      placeholderText="📅Check in - Check out"
      selected={startDate}
      onChange={onChange}
      startDate={startDate}
      endDate={endDate}
      selectsRange={true}
      monthsShown={2}
      minDate={new Date()}
      locale={es}
      inline
      excludeDates={
        typeof data === 'string' ? [] :
        data && data.map((fecha) => addDays(new Date(fecha), 1))
      }
    />
  );
};

export default Calendar;
