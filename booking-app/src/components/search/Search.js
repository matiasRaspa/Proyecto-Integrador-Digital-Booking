import React, { useContext, useState } from 'react'
import { FilterContext } from '../../FilterContext';
import Calendar from "./Calendar";
import { SelectCity } from './SelectCity';

export const Search = () => {

    const [selectedOption, setSelectedOption] = useState(null);
    const [reservationDate, setReservationDate] = useState({
        startDate: null,
        endDate: null
    });
    const { setValuesForm } = useContext(FilterContext);

    const { startDate, endDate } = reservationDate;

    const formatDate = (date) => {
        let formatted_date = null;
        if(date){
            formatted_date = date.getDate() + "-" + (date.getMonth() + 1) + "-" + date.getFullYear()
        }
        
        return formatted_date;
    }
    

    const handleSubmit = (e) => {
        e.preventDefault()
        setValuesForm(prevState => {
            return {
                ...prevState,
                city: selectedOption && selectedOption.value,
                date: reservationDate
            };
        });
        let startDate = formatDate(reservationDate.startDate);
        let endDate = formatDate(reservationDate.endDate);
        console.log(startDate, endDate);
    }

    return (
        <div className='search'>
            <h1>Busca ofertas en hoteles, casas y mucho más</h1>
            <form onSubmit={(e) => handleSubmit(e)} className='search__form'>

                <SelectCity selectedOption={selectedOption} setSelectedOption={setSelectedOption} />
                <Calendar startDate={startDate} endDate={endDate} setReservationDate={setReservationDate} />

                <button type="submit" className="search__btn">Buscar</button>
            </form>
        </div>
    )
}


