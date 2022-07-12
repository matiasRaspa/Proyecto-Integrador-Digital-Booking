
export const fechPost = async (url, data) => {

  const resp = await fetch(url, {
    method: "POST",
    headers: {
      "Content-Type":
        "application/json",
    },
    body: JSON.stringify(data)
  });

  const status = await resp.status;

  const { jwt } = await resp.json();
  
  const userData = {
    jwt,
    status
  }

  return userData;
}
