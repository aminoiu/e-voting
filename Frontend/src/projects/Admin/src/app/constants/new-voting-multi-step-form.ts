/*
New voting form elements
 */
const VOTING_CATEGORIES = [
  {name: 'Administrativ'},
  {name: 'Educație'}  // TODO: Add more categories. The reason: for statistics.
];

const ADD_VOTERS_MODE = [
  {name: 'Manual'},
  {name: 'Import'}
];

const ADD_CNDIDATES_MODE = [
  {name: 'Manual'},
  {name: 'Import'}
];

const VOTING_TYPE = [
  {name: 'Candidați'},
  {name: 'Opțiuni'}
];

const FORM_STEP_1 = { // name: {type: '', validations: {}, errors: {}, placeholder: ''}
  title: {
    type: 'text', validations: { required: false},
    errors: {}, placeholder: 'Crează o nouă sesiune de vot'
  },
  voting_title: {
    type: 'text', validations: {required: false}, errors: {},
    placeholder: 'Voting votingTitle'
  },
  voting_type: {
    type: 'select', options: VOTING_TYPE, validations: {required: true},
    errors: {
      required: 'This field can not be left blank'
    }, placeholder: 'Voting type'
  },
  voting_category: {
    type: 'select', options: VOTING_CATEGORIES, validations: {required: true},
    errors: {
      required: 'This field can not be left blank'
    }, placeholder: 'Categories'
  },
  voting_start_date: {
    type: 'date',
    validations: {required: true},
    errors: {
      required: 'This field can not be left blank'
    },
    placeholder: 'Voting start date'
  },
  voting_end_date: {
    type: 'date',
    validations: {required: true},
    errors: {
      required: 'This field can not be left blank'
    },
    placeholder: 'Voting end date'
  },
  voting_start_time: {
    type: 'time',
    validations: {required: true},
    errors: {
      required: 'This field can not be left blank'
    },
    placeholder: 'Voting start time'
  },
  voting_end_time: {
    type: 'date',
    validations: {required: true},
    errors: {
      required: 'This field can not be left blank'
    },
    placeholder: 'Voting end time'
  },
  add_voters_mode: {
    type: 'select',
    options: ADD_VOTERS_MODE,
    validations: {required: true},
    errors: {required: 'This field can not be left blank'},
    placeholder: 'Choose how to add votersList'
  },
  add_candidates_mode: {
    type: 'select',
    options: ADD_CNDIDATES_MODE,
    validations: {required: true},
    errors: {required: 'This field can not be left blank'},
    placeholder: 'Choose how to add candidatesList'
  }
};

const FORM_STEP_2 = {
  title: {
    type: 'text', validations: {required: false},
    errors: {}, placeholder: 'Add eligible votersList'
  },
  voting_title: {
    type: 'text', validations: {required: false},
    errors: {}, placeholder: ''
  },
  voter_name: {
    type: 'text', validations: {
      required: true
    },
    errors: {
      required: 'This field can not be left blank'
    }, placeholder: 'Name'
  },
  voter_email: {
    type: 'text', validations: {
      required: true
    },
    errors: {
      required: 'This field can not be left blank',
      email: 'The email has not the correct form'
    }, placeholder: 'Email'
  }
};
const FORM_STEP_3 = {
  title: {
    type: 'text', validations: {required: false},
    errors: {}, placeholder: 'Add voting candidatesList'
  },
  voting_title: {
    type: 'text', validations: {required: false},
    errors: {}, placeholder: ''
  },
  voter_name: {
    type: 'text', validations: {
      required: true
    },
    errors: {
      required: 'This field can not be left blank'
    }, placeholder: 'Name'
  },
  voter_email: {
    type: 'text', validations: {
      required: true
    },
    errors: {
      required: 'This field can not be left blank',
      email: 'The email has not the correct form'
    }, placeholder: 'Email'
  },
};
const FORM_STEP_4 = {
  title: {
    type: 'text', validations: {required: false},
    errors: {}, placeholder: 'Informații despre alegeri'
  },
  voting_title: {
    type: 'text', validations: {required: false},
    errors: {}, placeholder: ''
  },
  voters_number: {
    type: 'text', validations: {required: false},
    errors: {
    }, placeholder: 'Numărul de alegători'
  },
  candidates_number: {
    type: 'text', validations: {
    },
    errors: {
    }, placeholder: 'Numărul de candidați'
  },
  start_details: {
    type: 'dateTime', validations: {
    },
    errors: {
    }, placeholder: 'Start date and time'
  },
  end_details: {
    type: 'dateTime', validations: {
    },
    errors: {
    }, placeholder: 'End date and time'
  },
};


const STEP_ITEMS = [
  {label: 'Create a voting session', data: FORM_STEP_1},
  {label: 'Add eligible votersList', data: FORM_STEP_2},
  {label: 'Add voting candidatesList', data: FORM_STEP_3},
  {label: 'Voting information', data: FORM_STEP_4},
  {label: 'Review & Submit', data: {}}
];

export {STEP_ITEMS};
