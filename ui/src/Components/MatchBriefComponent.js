import React, { useState } from 'react';
function MatchBriefComponent()
{
  const [count, setCount] = useState(0);
  const [newtitle,setnewtitle]=useState("");

   function changehandler(event)
       {
          setnewtitle(event.target.value);
       }

return (
    <div><h3>MatchBriefComponent</h3>
      <p>You clicked {count} times</p>
      <button onClick={() => setCount(count + 1)}>
        Click me
      </button>

      <input type="text" value={newtitle} onChange={ changehandler}></input>
      <p>Current Title: {newtitle}</p>
    </div>

  );
}
export default MatchBriefComponent;