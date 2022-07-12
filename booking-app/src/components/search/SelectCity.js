import React from 'react'
import { useMediaQuery } from 'react-responsive';
import Select from 'react-select';
import { useFetch } from '../../hooks/useFetch';



export const SelectCity = ({ selectedOption, setSelectedOption }) => {

    const [data] = useFetch('http://grupo6c1621back-env.eba-b34jmgbs.us-east-2.elasticbeanstalk.com/cities');

    const isMobile = useMediaQuery({ maxWidth: 480 })
    const isTablet = useMediaQuery({ maxWidth: 768 })

    const options = data && data.map((city) => {
        return (
            { value: city.name, label: city.fullName }
        )
    })

    const getWidthByDevice = () => {

        let getWidth = 422

        if (isMobile) {
            getWidth = 370;
        } else if (isTablet) {
            getWidth = 250;
        }
        return getWidth;
    }

    const customStyles = {
        option: (provided, state) => ({
            textAlign: 'left',
            padding: 20,
            color: state.isFocused ? '#F0572D' : '#31363F',
            cursor: 'pointer',

        }),
        control: (provided, state) => ({
            ...provided,

            width: getWidthByDevice(),
            textAlign: isMobile ? 'center' : 'left',
            border: state.isFocused ? " 0px solid white" : " 0px solid white",
            boxShadow: state.isFocused ? 'none' : "none",
            "&:hover": {
                border: '',
                boxShadow: 'none'
            }
        }),

        singleValue: (provided, state) => {

            const transition = 'opacity 300ms';
            return { ...provided, transition };
        }
    }

    return (
        <Select
            styles={customStyles}
            placeholder={'✈¿A dónde vamos?'}
            className='search__select '
            isClearable={true}
            autoFocus={false}
            defaultValue={selectedOption}
            onChange={setSelectedOption}
            options={options}
        />
    )
}
