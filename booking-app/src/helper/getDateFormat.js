export const getDateFormat = {

  getFullDate: (date) => {
    return (
      date.getFullYear() + "-" + ("0" + (date.getMonth() + 1)).slice(-2) + "-" + ("0" + date.getDate()).slice(-2)
    );
  },

  getDate: (date) => {
    return (
      ("0" + date.getDate()).slice(-2) + "-" + ("0" + (date.getMonth() + 1)).slice(-2) + "-" + date.getFullYear()
    )
  }
};