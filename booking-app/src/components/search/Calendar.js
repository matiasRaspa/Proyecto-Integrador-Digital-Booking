import DatePicker from "react-datepicker";
import 'react-datepicker/dist/react-datepicker.css';
import React from "react"
import { es } from 'date-fns/locale'
import "../../styles/components/_calendar.scss"

function Calendar({ startDate, endDate, setReservationDate }) {

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
    />
  );
};

export default Calendar;
