import React, { useState } from "react";
import { Body } from "./components/body/Body";
import { Search } from "./components/search/Search";
import { FilterContext } from "./FilterContext";


function App() {

  const [selectedCategory, setSelectedCategory] = useState(null);
  const [valuesForm, setValuesForm] = useState({
    city: null,
    date: {
      startDate: null,
      endDate: null
    }
  });

  return (
    <FilterContext.Provider value={{
      valuesForm,
      setValuesForm,
      selectedCategory,
      setSelectedCategory
    }}>
      <Search />
      <Body />
    </FilterContext.Provider>
  );
}

export default App;
