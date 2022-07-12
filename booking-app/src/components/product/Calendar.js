import DatePicker from "react-datepicker";
import 'react-datepicker/dist/react-datepicker.css';
import React, { useState } from "react"
import { es } from 'date-fns/locale'
import { addDays } from "date-fns"
import "../../styles/components/_calendarProduct.scss"
import { useFetch } from "../../hooks/useFetch";
import { useParams } from "react-router-dom";

function Calendar() {

  const { id } = useParams();

  const [data] = useFetch(
    `http://grupo6c1621back-env.eba-b34jmgbs.us-east-2.elasticbeanstalk.com/bookings/product/${id}/bookingDates`
  );
  
  const [startDate, setStartDate] = useState(null);
  const [endDate, setEndDate] = useState(null);
  const onChange = (dates) => {
    const [start, end] = dates;
    setStartDate(start);
    setEndDate(end);
  };

  return (
    <DatePicker
      className="search__input"
      popperPlacement="auto"
      placeholderText="📅Check in - Check out"
      monthsShown={2}
      minDate={new Date()}
      locale={es}
      inline="true"

      excludeDates={
        typeof data === 'string' ? [] :
          data && data.map((fecha) => addDays(new Date(fecha), 1))
        }
    />
  );
};

export default Calendar;